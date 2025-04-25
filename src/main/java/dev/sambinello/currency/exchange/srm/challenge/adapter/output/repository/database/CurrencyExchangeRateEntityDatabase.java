package dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.database;

import dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.entity.CurrencyEntity;
import dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.entity.CurrencyExchangeRateEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyExchangeRateEntityDatabase extends CrudRepository<CurrencyExchangeRateEntity, Long> {

    List<CurrencyExchangeRateEntity> findAllByOriginCurrency(CurrencyEntity currencyEntity);

    Optional<CurrencyExchangeRateEntity> findByOriginCurrencyAndTargetCurrency(
            CurrencyEntity originCurrency,
            CurrencyEntity targetCurrency);

}
