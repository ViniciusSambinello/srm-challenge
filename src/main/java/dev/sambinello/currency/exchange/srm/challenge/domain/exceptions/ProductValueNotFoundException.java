package dev.sambinello.currency.exchange.srm.challenge.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class ProductValueNotFoundException extends HttpStatusCodeException {

    public ProductValueNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Product value not found.");
    }
}

