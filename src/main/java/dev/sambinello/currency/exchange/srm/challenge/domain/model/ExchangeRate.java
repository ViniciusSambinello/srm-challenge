package dev.sambinello.currency.exchange.srm.challenge.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Getter
public class ExchangeRate {

    private final Currency origin;
    private final Currency target;
    private final BigDecimal value;

}
