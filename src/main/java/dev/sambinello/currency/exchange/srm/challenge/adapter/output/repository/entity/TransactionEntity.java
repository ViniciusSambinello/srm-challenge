package dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACTIONS")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private ProductEntity product;

    @Column(name = "EXCHANGE_RATE")
    private BigDecimal exchangeRate;

    @ManyToOne
    @JoinColumn(name = "ORIGIN_REALM_ID")
    private RealmEntity originRealm;

    @ManyToOne
    @JoinColumn(name = "TARGET_REALM_ID")
    private RealmEntity targetRealm;

    @ManyToOne
    @JoinColumn(name = "ORIGIN_CURRENCY_ID")
    private CurrencyEntity originCurrency;

    @ManyToOne
    @JoinColumn(name = "TARGET_CURRENCY_ID")
    private CurrencyEntity targetCurrency;

    @Column(name = "ORIGIN_TRANSACTION_VALUE")
    private BigDecimal originTransactionValue;

    @Column(name = "TARGET_TRANSACTION_VALUE")
    private BigDecimal targetTransactionValue;

    @CreatedDate
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    public TransactionEntity() {
    }

    public TransactionEntity(
            Long id, ProductEntity product, BigDecimal exchangeRate, RealmEntity originRealm,
            RealmEntity targetRealm, CurrencyEntity originCurrency, CurrencyEntity targetCurrency,
            BigDecimal originTransactionValue, BigDecimal targetTransactionValue, LocalDateTime createdAt) {
        this.id = id;
        this.product = product;
        this.exchangeRate = exchangeRate;
        this.originRealm = originRealm;
        this.targetRealm = targetRealm;
        this.originCurrency = originCurrency;
        this.targetCurrency = targetCurrency;
        this.originTransactionValue = originTransactionValue;
        this.targetTransactionValue = targetTransactionValue;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public RealmEntity getOriginRealm() {
        return originRealm;
    }

    public RealmEntity getTargetRealm() {
        return targetRealm;
    }

    public CurrencyEntity getOriginCurrency() {
        return originCurrency;
    }

    public CurrencyEntity getTargetCurrency() {
        return targetCurrency;
    }

    public BigDecimal getOriginTransactionValue() {
        return originTransactionValue;
    }

    public BigDecimal getTargetTransactionValue() {
        return targetTransactionValue;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
