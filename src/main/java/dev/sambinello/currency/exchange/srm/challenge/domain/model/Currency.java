package dev.sambinello.currency.exchange.srm.challenge.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Currency {

    private final String id;
    private final String name;

}
