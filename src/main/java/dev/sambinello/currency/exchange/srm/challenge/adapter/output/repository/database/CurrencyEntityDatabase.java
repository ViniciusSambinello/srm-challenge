package dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.database;

import dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.entity.CurrencyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyEntityDatabase extends CrudRepository<CurrencyEntity, Long> {
}
