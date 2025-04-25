package dev.sambinello.currency.exchange.srm.challenge.domain.ports.output;

import dev.sambinello.currency.exchange.srm.challenge.domain.model.Product;

import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(String productId);

}
