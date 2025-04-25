package dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository;

import dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.database.CurrencyEntityDatabase;
import dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.database.CurrencyExchangeRateEntityDatabase;
import dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.entity.CurrencyEntity;
import dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.entity.CurrencyExchangeRateEntity;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.Currency;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.ExchangeRate;
import dev.sambinello.currency.exchange.srm.challenge.domain.ports.output.ExchangeRateRepository;
import lombok.val;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ExchangeRateRepositoryAdapterImpl implements ExchangeRateRepository {

    private final CurrencyEntityDatabase currencyEntityDatabase;
    private final CurrencyExchangeRateEntityDatabase database;

    public ExchangeRateRepositoryAdapterImpl(CurrencyEntityDatabase currencyEntityDatabase, CurrencyExchangeRateEntityDatabase database) {
        this.currencyEntityDatabase = currencyEntityDatabase;
        this.database = database;
    }

    @Override
    public List<ExchangeRate> findAllExchangeRatesByCurrency(Currency currency) {
        final CurrencyEntity currencyEntity = currencyEntityDatabase.findById(Long.valueOf(currency.getId())).orElseThrow();
        return database.findAllByOriginCurrency(currencyEntity)
                .stream().map(currencyExchangeRateEntity -> ExchangeRate.builder()
                        .origin(Currency.builder()
                                .id(currencyEntity.getId().toString())
                                .name(currencyEntity.getName())
                                .build())
                        .target(Currency.builder()
                                .id(currencyExchangeRateEntity.getTargetCurrency().getId().toString())
                                .name(currencyExchangeRateEntity.getTargetCurrency().getName())
                                .build())
                        .value(currencyExchangeRateEntity.getExchangeRate())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BigDecimal> findExchangeRateByOriginAndTargetCurrencies(Currency origin, Currency target) {
        final CurrencyEntity originCurrency = currencyEntityDatabase.findById(Long.valueOf(origin.getId()))
                .orElseThrow();
        final CurrencyEntity targetCurrency = currencyEntityDatabase.findById(Long.valueOf(target.getId()))
                .orElseThrow();

        return database.findByOriginCurrencyAndTargetCurrency(originCurrency, targetCurrency)
                .map(CurrencyExchangeRateEntity::getExchangeRate);
    }
}
