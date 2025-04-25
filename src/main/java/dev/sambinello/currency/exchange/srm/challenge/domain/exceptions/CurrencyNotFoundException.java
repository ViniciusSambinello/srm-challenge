package dev.sambinello.currency.exchange.srm.challenge.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class CurrencyNotFoundException extends HttpStatusCodeException {

    public CurrencyNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Currency not found.");
    }
}
