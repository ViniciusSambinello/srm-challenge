package dev.sambinello.currency.exchange.srm.challenge.domain.usecases.handler;

import dev.sambinello.currency.exchange.srm.challenge.domain.model.Currency;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.ProductValue;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.Realm;

import java.math.BigDecimal;

public interface ProductRateCalculatorHandler {

    BigDecimal getTargetTransactionValue(
            ProductValue productValue, Integer productQuantity, Currency target,
            BigDecimal currencyExchangeRate, Realm originRealm, Realm targetRealm);

    BigDecimal getRate(ProductValue productValue, Currency target, BigDecimal currencyExchangeRate, Realm originRealm,
                       Realm targetRealm);

    String getProductId();

}
