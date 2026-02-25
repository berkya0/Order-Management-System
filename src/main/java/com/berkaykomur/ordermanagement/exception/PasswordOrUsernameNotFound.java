package com.berkaykomur.ordermanagement.exception;

import org.springframework.http.HttpStatus;

public class PasswordOrUsernameNotFound extends BaseException {
    public PasswordOrUsernameNotFound(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
