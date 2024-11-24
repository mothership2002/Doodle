package com.example.webflux.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<Map<String, String>> handleWebExchangeBindException(WebExchangeBindException e) {
        return ResponseEntity.badRequest().body(
                e.getBindingResult()
                        .getFieldErrors().stream()
                        .collect(Collectors.toMap(
                                FieldError::getField,
                                fieldError -> Optional.ofNullable(fieldError.getDefaultMessage())
                                        .orElse("Haven't message")
                        )));
    }
    // custom Exception?
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException e) {
        // magic number ...
        return ResponseEntity.badRequest().body(Map.of(e.getMessage().split(" ")[0], e.getMessage()));
    }

    // ------------------------- aspect ------------------------- //
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Void> handleRuntimeExceptionLog(RuntimeException e) {
        return ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleExceptionLog(Exception e) {
        return ResponseEntity.internalServerError().build();
    }
}
