package dev.sambinello.currency.exchange.srm.challenge.domain.ports.output;

import dev.sambinello.currency.exchange.srm.challenge.domain.model.Currency;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.Product;
import dev.sambinello.currency.exchange.srm.challenge.domain.model.ProductValue;

import java.util.Optional;

public interface ProductValueRepository {

    Optional<ProductValue> findByProductAndCurrency(Product product, Currency currency);

}
