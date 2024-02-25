package com.example.scharge.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class QuotationException extends RuntimeException {

    public QuotationException(String message) {
        super(message);
    }

    public QuotationException(String message, Throwable cause) {
        super(message, cause);
    }
}
