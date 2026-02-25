package com.berkaykomur.ordermanagement.exception;

import org.springframework.http.HttpStatus;

public class EmailAlreadyExitsException extends BaseException {
    public EmailAlreadyExitsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
