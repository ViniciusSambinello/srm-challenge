package dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository;

import dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.database.CurrencyEntityDatabase;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.Currency;
import dev.sambinello.currency.exchange.srm.challenge.domain.ports.output.CurrencyRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CurrencyRepositoryAdapterImpl implements CurrencyRepository {

    private final CurrencyEntityDatabase database;

    public CurrencyRepositoryAdapterImpl(CurrencyEntityDatabase database) {
        this.database = database;
    }

    @Override
    public Optional<Currency> findById(String currencyId) {
        return database.findById(Long.valueOf(currencyId))
                .map(currencyEntity -> new Currency(currencyEntity.getId().toString(), currencyEntity.getName()));
    }
}
