package dev.sambinello.currency.exchange.srm.challenge.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class ProductNotFoundException extends HttpStatusCodeException {

    public ProductNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Product not found.");
    }
}
