package dev.sambinello.currency.exchange.srm.challenge.domain.ports.output;

import dev.sambinello.currency.exchange.srm.challenge.domain.model.Currency;

import java.util.Optional;

public interface CurrencyRepository {

    Optional<Currency> findById(String currencyId);

}
