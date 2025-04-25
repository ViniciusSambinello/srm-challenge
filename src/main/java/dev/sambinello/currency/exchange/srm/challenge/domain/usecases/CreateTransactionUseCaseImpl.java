package dev.sambinello.currency.exchange.srm.challenge.domain.usecases;

import dev.sambinello.currency.exchange.srm.challenge.domain.exceptions.*;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.*;
import dev.sambinello.currency.exchange.srm.challenge.domain.ports.input.CreateTransactionUseCase;
import dev.sambinello.currency.exchange.srm.challenge.domain.ports.output.*;
import dev.sambinello.currency.exchange.srm.challenge.domain.usecases.handler.DefaultProductRateCalculatorHandler;
import dev.sambinello.currency.exchange.srm.challenge.domain.usecases.handler.ProductRateCalculatorHandler;
import dev.sambinello.currency.exchange.srm.challenge.domain.viewmodel.CreateTransactionCommand;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CreateTransactionUseCaseImpl implements CreateTransactionUseCase {

    private final ProductRepository productRepository;
    private final RealmRepository realmRepository;
    private final CurrencyRepository currencyRepository;
    private final ExchangeRateRepository exchangeRateRepository;
    private final TransactionRepository transactionRepository;
    private final ProductValueRepository productValueRepository;

    private final List<ProductRateCalculatorHandler> handlers;

    public CreateTransactionUseCaseImpl(
            ProductRepository productRepository, RealmRepository realmRepository, CurrencyRepository currencyRepository,
            ExchangeRateRepository exchangeRateRepository, TransactionRepository transactionRepository,
            ProductValueRepository productValueRepository, List<ProductRateCalculatorHandler> handlers) {
        this.productRepository = productRepository;
        this.realmRepository = realmRepository;
        this.currencyRepository = currencyRepository;
        this.exchangeRateRepository = exchangeRateRepository;
        this.transactionRepository = transactionRepository;
        this.productValueRepository = productValueRepository;
        this.handlers = handlers;
    }

    @Override
    public Transaction execute(CreateTransactionCommand command) {
        final Product product = productRepository.findById(command.getProductId())
                .orElseThrow(ProductNotFoundException::new);
        final Realm originRealm = realmRepository.findById(command.getOriginRealmId())
                .orElseThrow(RealmNotFoundException::new);
        final Realm targetRealm = realmRepository.findById(command.getTargetRealmId())
                .orElseThrow(RealmNotFoundException::new);
        final Currency originCurrency = currencyRepository.findById(command.getOriginCurrencyId())
                .orElseThrow(CurrencyNotFoundException::new);
        final Currency targetCurrency = currencyRepository.findById(command.getTargetCurrencyId())
                .orElseThrow(CurrencyNotFoundException::new);

        final ProductValue productValue = productValueRepository.findByProductAndCurrency(product, originCurrency)
                .orElseThrow(ProductValueNotFoundException::new);
        final BigDecimal baseRate = exchangeRateRepository.findExchangeRateByOriginAndTargetCurrencies(originCurrency, targetCurrency)
                .orElseThrow(ExchangeRateNotFoundException::new);

        final ProductRateCalculatorHandler handler = handlers.stream()
                .filter(it -> it.getProductId().equals(product.getId()))
                .findFirst()
                .orElse(new DefaultProductRateCalculatorHandler());

        final BigDecimal originTransactionValue = productValue.getValue().multiply(BigDecimal.valueOf(command.getProductQuantity()));
        final BigDecimal finalRate = handler.getRate(productValue, targetCurrency, baseRate, originRealm, targetRealm);
        final BigDecimal targetTransactionValue = handler.getTargetTransactionValue(productValue, command.getProductQuantity(), targetCurrency, baseRate, originRealm, targetRealm);

        final Transaction transactionToCreate = Transaction.builder()
                .exchangeRate(finalRate)
                .product(product)
                .originRealm(originRealm)
                .originCurrency(originCurrency)
                .originTransactionValue(originTransactionValue)
                .targetRealm(targetRealm)
                .targetCurrency(targetCurrency)
                .targetTransactionValue(targetTransactionValue)
                .created(command.getCreated())
                .build();

        return transactionRepository.create(transactionToCreate);
    }
}
