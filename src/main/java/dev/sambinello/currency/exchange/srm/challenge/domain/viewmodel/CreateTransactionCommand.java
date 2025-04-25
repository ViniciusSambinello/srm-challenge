package dev.sambinello.currency.exchange.srm.challenge.domain.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
public class CreateTransactionCommand {

    private final String productId;
    private final Integer productQuantity;
    private final String originRealmId;
    private final String originCurrencyId;
    private final String targetRealmId;
    private final String targetCurrencyId;
    private final LocalDateTime created;

}
