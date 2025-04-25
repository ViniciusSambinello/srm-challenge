package dev.sambinello.currency.exchange.srm.challenge.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
public class Transaction {

    @Setter
    private String id;

    private final BigDecimal exchangeRate;

    private final Product product;

    private final Realm originRealm;
    private final Currency originCurrency;
    private final BigDecimal originTransactionValue;

    private final Realm targetRealm;
    private final Currency targetCurrency;
    private final BigDecimal targetTransactionValue;

    private final LocalDateTime created;

}
