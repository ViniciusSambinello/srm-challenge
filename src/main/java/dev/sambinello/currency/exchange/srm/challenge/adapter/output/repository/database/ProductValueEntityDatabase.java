package dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.database;

import dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.entity.ProductValueEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductValueEntityDatabase extends CrudRepository<ProductValueEntity, Long> {

    Optional<ProductValueEntity> findByProductIdAndCurrencyId(Long productId, Long currencyId);
}
