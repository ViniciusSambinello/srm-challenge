package dev.sambinello.currency.exchange.srm.challenge.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class RealmNotFoundException extends HttpStatusCodeException {

    public RealmNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Realm not found.");
    }
}
