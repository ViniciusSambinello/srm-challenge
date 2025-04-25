package dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository;

import dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.database.ProductEntityDatabase;
import dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.database.ProductValueEntityDatabase;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.Currency;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.Product;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.ProductValue;
import dev.sambinello.currency.exchange.srm.challenge.domain.ports.output.ProductValueRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductValueRepositoryImpl implements ProductValueRepository {

    private final ProductValueEntityDatabase database;

    public ProductValueRepositoryImpl(ProductValueEntityDatabase database) {
        this.database = database;
    }
    @Override
    public Optional<ProductValue> findByProductAndCurrency(Product product, Currency currency) {
        return database.findByProductIdAndCurrencyId(Long.valueOf(product.getId()), Long.valueOf(currency.getId()))
                .map(productValueEntity -> new ProductValue(product, currency, productValueEntity.getValue()));
    }
}
