package dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository;

import dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.database.CurrencyEntityDatabase;
import dev.sambinello.currency.exchange.srm.challenge.adapter.output.repository.database.ProductEntityDatabase;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.Product;
import dev.sambinello.currency.exchange.srm.challenge.domain.ports.output.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductRepositoryAdapterImpl implements ProductRepository {

    private final ProductEntityDatabase database;

    public ProductRepositoryAdapterImpl(ProductEntityDatabase database) {
        this.database = database;
    }

    @Override
    public Optional<Product> findById(String productId) {
        return database.findById(Long.valueOf(productId))
                .map(productEntity -> new Product(productEntity.getId().toString(), productEntity.getName()));
    }
}
