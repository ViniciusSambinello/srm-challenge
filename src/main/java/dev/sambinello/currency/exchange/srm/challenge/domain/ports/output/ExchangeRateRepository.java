package dev.sambinello.currency.exchange.srm.challenge.domain.ports.output;

import dev.sambinello.currency.exchange.srm.challenge.domain.model.Currency;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.ExchangeRate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ExchangeRateRepository {

    List<ExchangeRate> findAllExchangeRatesByCurrency(Currency currency);
    Optional<BigDecimal> findExchangeRateByOriginAndTargetCurrencies(Currency origin, Currency target);

}
