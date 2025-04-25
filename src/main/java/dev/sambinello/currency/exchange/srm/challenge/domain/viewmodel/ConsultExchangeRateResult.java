package dev.sambinello.currency.exchange.srm.challenge.domain.viewmodel;

import dev.sambinello.currency.exchange.srm.challenge.domain.model.ExchangeRate;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class ConsultExchangeRateResult {

    private final String currencyId;
    private final BigDecimal rate;

    public ConsultExchangeRateResult(ExchangeRate exchangeRate) {
        this.currencyId = exchangeRate.getTarget().getId();
        this.rate = exchangeRate.getValue();
    }

}
