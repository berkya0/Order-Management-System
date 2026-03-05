package com.berkaykomur.ordermanagement.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthException(AuthenticationException ex) {
        ApiError apiError= ApiError.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message("password or username is incorrect")
                .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiError> handleException(BaseException e, HttpServletRequest request) {
        ApiError apiError =ApiError.builder()
                .error(e.getStatus().getReasonPhrase())
                .status(e.getStatus().value())
                .path(request.getRequestURI())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(e.getStatus()).body(apiError);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception e,
                                                    HttpServletRequest request) {
        ApiError apiError = ApiError.builder()
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .path(request.getRequestURI())
                .message("Unexpected error occurred")
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(apiError);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException e,
            HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ApiError apiError =ApiError.builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .message("Validation error")
                .validationErrors(errors)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }



}
