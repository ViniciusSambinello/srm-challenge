package dev.sambinello.currency.exchange.srm.challenge.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class RealmNotFoundException extends HttpStatusCodeException {

    public RealmNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Realm not found.");
    }

    @ControllerAdvice
    public static class RealmNotFoundExceptionHandler {

        @ResponseStatus(HttpStatus.NOT_FOUND)
        @ResponseBody
        @ExceptionHandler(RealmNotFoundException.class)
        public String handler(RealmNotFoundException exception){
            return "Reino não encontrado.";
        }

    }
}
