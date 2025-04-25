package dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCT_VALUE")
public class ProductValueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "CURRENCY_ID")
    private CurrencyEntity currency;

    @Column(name = "PRODUCT_VALUE")
    private BigDecimal value;

    @Deprecated
    public ProductValueEntity() {
    }

    public ProductValueEntity(Long id, ProductEntity product, CurrencyEntity currency, BigDecimal value) {
        this.id = id;
        this.product = product;
        this.currency = currency;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public CurrencyEntity getCurrency() {
        return currency;
    }

    public BigDecimal getValue() {
        return value;
    }
}
