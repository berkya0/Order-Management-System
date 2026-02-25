package com.berkaykomur.ordermanagement.exception;

import org.springframework.http.HttpStatus;

public class ExternalServiceException extends BaseException {

    public ExternalServiceException(String message, HttpStatus status) {
        super(message,status);

    }

}
