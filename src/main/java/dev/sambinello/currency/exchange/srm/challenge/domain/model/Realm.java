package dev.sambinello.currency.exchange.srm.challenge.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Realm {

    private final String id;
    private final String name;
    private final Currency currency;

}