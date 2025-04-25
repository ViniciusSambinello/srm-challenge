package dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.database;

import dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.entity.RealmEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealmEntityDatabase extends CrudRepository<RealmEntity, Long> {
}
