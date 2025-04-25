package dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.database;

import dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.entity.RealmEntity;
import dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionEntityDatabase extends CrudRepository<TransactionEntity, Long> {

    List<TransactionEntity> findAllByOriginRealm(RealmEntity realmEntity);

}
