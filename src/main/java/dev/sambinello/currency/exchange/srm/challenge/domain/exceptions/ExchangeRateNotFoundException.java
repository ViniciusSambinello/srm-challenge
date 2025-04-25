package dev.sambinello.currency.exchange.srm.challenge.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class ExchangeRateNotFoundException extends HttpStatusCodeException {

    public ExchangeRateNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Exchange rate not found.");
    }
}
