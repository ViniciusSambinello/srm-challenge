package dev.sambinello.currency.exchange.srm.challenge.domain.usecases.handler;

import dev.sambinello.currency.exchange.srm.challenge.domain.model.Currency;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.ProductValue;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.Realm;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SkinProductRateCalculatorHandler implements ProductRateCalculatorHandler {

    private final static BigDecimal SKIN_EXCHANGE_RATE = new BigDecimal("1.05");

    @Override
    public BigDecimal getTargetTransactionValue(ProductValue productValue, Integer productQuantity, Currency target, BigDecimal currencyExchangeRate, Realm originRealm, Realm targetRealm) {
        return currencyExchangeRate.multiply(productValue.getValue())
                .multiply(BigDecimal.valueOf(productQuantity))
                .multiply(SKIN_EXCHANGE_RATE);
    }

    @Override
    public BigDecimal getRate(ProductValue productValue, Currency target, BigDecimal currencyExchangeRate, Realm originRealm, Realm targetRealm) {
        return currencyExchangeRate.multiply(SKIN_EXCHANGE_RATE);
    }

    @Override
    public String getProductId() {
        return "1";
    }
}
