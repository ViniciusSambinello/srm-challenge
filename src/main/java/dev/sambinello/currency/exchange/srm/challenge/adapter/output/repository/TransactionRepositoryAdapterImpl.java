package dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository;

import dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.database.CurrencyEntityDatabase;
import dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.database.ProductEntityDatabase;
import dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.database.RealmEntityDatabase;
import dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.database.TransactionEntityDatabase;
import dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.entity.CurrencyEntity;
import dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.entity.ProductEntity;
import dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.entity.RealmEntity;
import dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.entity.TransactionEntity;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.Currency;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.Product;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.Realm;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.Transaction;
import dev.sambinello.currency.exchange.srm.challenge.domain.ports.output.TransactionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TransactionRepositoryAdapterImpl implements TransactionRepository {

    private final TransactionEntityDatabase database;
    private final RealmEntityDatabase realmEntityDatabase;
    private final ProductEntityDatabase productEntityDatabase;
    private final CurrencyEntityDatabase currencyEntityDatabase;

    public TransactionRepositoryAdapterImpl(TransactionEntityDatabase database, RealmEntityDatabase realmEntityDatabase,
                                            ProductEntityDatabase productEntityDatabase,
                                            CurrencyEntityDatabase currencyEntityDatabase) {
        this.database = database;
        this.realmEntityDatabase = realmEntityDatabase;
        this.productEntityDatabase = productEntityDatabase;
        this.currencyEntityDatabase = currencyEntityDatabase;
    }

    @Override
    public List<Transaction> findAllByRealm(Realm realm) {
        final RealmEntity realmEntity = realmEntityDatabase.findById(Long.valueOf(realm.getId())).orElseThrow();

        return database.findAllByOriginRealm(realmEntity).stream().map(transactionEntity -> Transaction.builder()
                        .id(transactionEntity.getId().toString())
                        .exchangeRate(transactionEntity.getExchangeRate())
                        .product(new Product(transactionEntity.getProduct().getId().toString(),
                                transactionEntity.getProduct().getName()))
                        .originRealm(new Realm(transactionEntity.getOriginRealm().getId().toString(),
                                transactionEntity.getOriginRealm().getName(),
                                new Currency(transactionEntity.getOriginRealm().getCurrency().getId().toString(),
                                        transactionEntity.getOriginRealm().getCurrency().getName())))
                        .originCurrency(new Currency(transactionEntity.getOriginCurrency().getId().toString(),
                                transactionEntity.getOriginCurrency().getName()))
                        .originTransactionValue(transactionEntity.getOriginTransactionValue())
                        .targetRealm(new Realm(transactionEntity.getTargetRealm().getId().toString(),
                                transactionEntity.getTargetRealm().getName(),
                                new Currency(transactionEntity.getTargetRealm().getCurrency().getId().toString(),
                                        transactionEntity.getTargetRealm().getCurrency().getName())))
                        .targetCurrency(new Currency(transactionEntity.getTargetCurrency().getId().toString(),
                                transactionEntity.getTargetCurrency().getName()))
                        .targetTransactionValue(transactionEntity.getTargetTransactionValue())
                        .created(transactionEntity.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Transaction create(Transaction transaction) {
        final ProductEntity productEntity = productEntityDatabase.findById(Long.valueOf(transaction.getProduct().getId()))
                .orElseThrow();

        final RealmEntity originRealm = realmEntityDatabase.findById(Long.valueOf(transaction.getOriginRealm().getId())).orElseThrow();
        final RealmEntity targetRealm = realmEntityDatabase.findById(Long.valueOf(transaction.getTargetRealm().getId())).orElseThrow();

        final CurrencyEntity originCurrency = currencyEntityDatabase.findById(Long.valueOf(transaction.getOriginCurrency().getId())).orElseThrow();
        final CurrencyEntity targetCurrency = currencyEntityDatabase.findById(Long.valueOf(transaction.getTargetCurrency().getId())).orElseThrow();

        final TransactionEntity entity = new TransactionEntity(null, productEntity, transaction.getExchangeRate(), originRealm,
                targetRealm, originCurrency, targetCurrency, transaction.getOriginTransactionValue(), transaction.getTargetTransactionValue(), transaction.getCreated());

        final TransactionEntity createdEntity = database.save(entity);

        transaction.setId(createdEntity.getId().toString());

        return transaction;
    }
}
