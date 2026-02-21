package com.berkaykomur.ordermanagement.exception;

import org.springframework.http.HttpStatus;

public class ExternalServiceException extends BaseException {

    private final HttpStatus status;
    public ExternalServiceException(String message, HttpStatus status) {
        super(message);
        this.status = status;

    }
    public HttpStatus getStatus() {
        return status;
    }
}
