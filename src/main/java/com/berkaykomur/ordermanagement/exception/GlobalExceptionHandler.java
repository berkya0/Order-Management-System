package com.berkaykomur.ordermanagement.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleException(ResourceNotFoundException e, HttpServletRequest request) {
        ApiError apiError =ApiError.builder()
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getRequestURI())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }
    @ExceptionHandler(ExternalServiceException.class)
    public ResponseEntity<ApiError> handleException(ExternalServiceException e, HttpServletRequest request) {
        ApiError apiError=ApiError.builder()
                .error(e.getStatus().getReasonPhrase())
                .status(e.getStatus().value())
                .path(request.getRequestURI())
                .message(e.getMessage())
                .build();
        return  ResponseEntity.status(e.getStatus()).body(apiError);
    }

}
