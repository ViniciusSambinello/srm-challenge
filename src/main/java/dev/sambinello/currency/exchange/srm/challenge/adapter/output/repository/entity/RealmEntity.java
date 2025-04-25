package dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "REALM")
public class RealmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToOne
    @JoinColumn(name = "CURRENCY_ID")
    private CurrencyEntity currency;

    @CreatedDate
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Deprecated
    public RealmEntity() {
    }

    public RealmEntity(Long id, String name, LocalDateTime createdAt, CurrencyEntity currency) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public CurrencyEntity getCurrency() {
        return currency;
    }
}
