package dev.sambinello.currency.exchange.srm.challenge.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Getter
public class ProductValue {

    private final Product product;
    private final Currency currency;
    private final BigDecimal value;

}
