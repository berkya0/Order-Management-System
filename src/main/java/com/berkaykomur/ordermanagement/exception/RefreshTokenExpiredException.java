package com.berkaykomur.ordermanagement.exception;

import org.springframework.http.HttpStatus;

public class RefreshTokenExpiredException extends BaseException {
    public RefreshTokenExpiredException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
