package dev.sambinello.currency.exchange.srm.challenge.domain.ports.output;

import dev.sambinello.currency.exchange.srm.challenge.domain.model.Realm;

import java.util.Optional;

public interface RealmRepository {

    Optional<Realm> findById(String realmId);

}
