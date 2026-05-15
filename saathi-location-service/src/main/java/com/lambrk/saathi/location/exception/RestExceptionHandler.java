package com.lambrk.saathi.location.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiError> validation(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
        return ResponseEntity.badRequest().body(new ApiError(false, "Invalid request", errors));
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiError> error(Exception ex) {
        return ResponseEntity.badRequest().body(new ApiError(false, ex.getMessage(), List.of()));
    }

    record ApiError(boolean success, String message, List<String> errors) {
    }
}
