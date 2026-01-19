package com.project.ecommerce.exception;

import com.project.ecommerce.utility.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<SimpleErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex) {

        SimpleErrorResponse response = SimpleErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();

        return ResponseBuilder.failure(HttpStatus.NOT_FOUND, response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FieldErrorResponse> handleFieldErrorException(
            MethodArgumentNotValidException ex) {

        List<CustomFieldError> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> new CustomFieldError(
                        err.getField(),
                        err.getDefaultMessage(),
                        err.getRejectedValue()
                ))
                .collect(Collectors.toList());

        return ResponseBuilder.failure(
                HttpStatus.BAD_REQUEST,
                "Invalid Inputs !!",
                errors
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<SimpleErrorResponse> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex) {

        SimpleErrorResponse response = SimpleErrorResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .message("Duplicate Value !!")
                .timeStamp(LocalDateTime.now())
                .build();

        return ResponseBuilder.failure(HttpStatus.CONFLICT, response);
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            NullPointerException.class,
            IllegalStateException.class,
            RuntimeException.class
    })
    public ResponseEntity<SimpleErrorResponse> handleRuntime(Exception ex) {

        SimpleErrorResponse response = SimpleErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();

        return ResponseBuilder.failure(HttpStatus.INTERNAL_SERVER_ERROR, response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<SimpleErrorResponse> handleAll(Exception ex) {

        SimpleErrorResponse response = SimpleErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();

        return ResponseBuilder.failure(HttpStatus.INTERNAL_SERVER_ERROR, response);
    }
}
