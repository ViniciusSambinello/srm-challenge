package dev.sambinello.currency.exchange.srm.challenge.domain.usecases;

import dev.sambinello.currency.exchange.srm.challenge.domain.exceptions.RealmNotFoundException;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.Realm;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.Transaction;
import dev.sambinello.currency.exchange.srm.challenge.domain.ports.input.ConsultTransactionsUseCase;
import dev.sambinello.currency.exchange.srm.challenge.domain.ports.output.RealmRepository;
import dev.sambinello.currency.exchange.srm.challenge.domain.ports.output.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultTransactionsUseCaseImpl implements ConsultTransactionsUseCase {

    private final RealmRepository realmRepository;
    private final TransactionRepository transactionRepository;

    public ConsultTransactionsUseCaseImpl(RealmRepository realmRepository, TransactionRepository transactionRepository) {
        this.realmRepository = realmRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> execute(String realmId) {
        final Realm realm = realmRepository.findById(realmId)
                .orElseThrow(RealmNotFoundException::new);

        return transactionRepository.findAllByRealm(realm);
    }
}
