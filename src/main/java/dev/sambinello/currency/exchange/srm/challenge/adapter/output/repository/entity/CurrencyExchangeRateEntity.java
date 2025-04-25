package dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "CURRENCY_EXCHANGE_RATE")
public class CurrencyExchangeRateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORIGIN_CURRENCY_ID")
    private CurrencyEntity originCurrency;

    @ManyToOne
    @JoinColumn(name = "TARGET_CURRENCY_ID")
    private CurrencyEntity targetCurrency;

    @Column(name = "EXCHANGE_RATE")
    private BigDecimal exchangeRate;

    @Deprecated
    public CurrencyExchangeRateEntity() {
    }

    public CurrencyExchangeRateEntity(
            Long id, CurrencyEntity originCurrency, CurrencyEntity targetCurrency,
            BigDecimal exchangeRate) {
        this.id = id;
        this.originCurrency = originCurrency;
        this.targetCurrency = targetCurrency;
        this.exchangeRate = exchangeRate;
    }

    public Long getId() {
        return id;
    }

    public CurrencyEntity getOriginCurrency() {
        return originCurrency;
    }

    public CurrencyEntity getTargetCurrency() {
        return targetCurrency;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }
}
