package com.example.webflux.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<Void> handleWebExchangeBindException(WebExchangeBindException e) {
        e.getBindingResult().getFieldErrors().forEach(fieldError -> log.error("{} : {}", fieldError.getField(), fieldError.getDefaultMessage()));
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Void> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleException(Exception e) {
        return ResponseEntity.internalServerError().build();
    }
}
