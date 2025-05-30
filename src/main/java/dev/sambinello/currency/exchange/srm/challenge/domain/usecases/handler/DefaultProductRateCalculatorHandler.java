package dev.sambinello.currency.exchange.srm.challenge.domain.usecases.handler;

import dev.sambinello.currency.exchange.srm.challenge.domain.model.Currency;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.ProductValue;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.Realm;

import java.math.BigDecimal;

public class DefaultProductRateCalculatorHandler implements ProductRateCalculatorHandler {

    @Override
    public BigDecimal getTargetTransactionValue(ProductValue productValue, Integer productQuantity, Currency target, BigDecimal currencyExchangeRate, Realm originRealm, Realm targetRealm) {
        return currencyExchangeRate.multiply(productValue.getValue())
                .multiply(BigDecimal.valueOf(productQuantity));
    }

    @Override
    public BigDecimal getRate(ProductValue productValue, Currency target, BigDecimal currencyExchangeRate, Realm originRealm, Realm targetRealm) {
        return currencyExchangeRate;
    }

    @Override
    public String getProductId() {
        throw new RuntimeException();
    }
}
