package com.example.cart.Utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseBuilder {

    public static <T> ResponseEntity<ResponseStructure<T>> success(
            HttpStatus httpStatus, String message, T data) {

        ResponseStructure<T> response = ResponseStructure.<T>builder()
                .status(httpStatus.value())
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.status(httpStatus).body(response);
    }

    public static ResponseEntity<FieldErrorResponse> failure(
            HttpStatus httpStatus, String message, List<CustomFieldError> errors) {

        FieldErrorResponse response = FieldErrorResponse.builder()
                .status(httpStatus.value())
                .message(message)
                .errors(errors)
                .build();

        return ResponseEntity.status(httpStatus).body(response);
    }

    public static ResponseEntity<SimpleErrorResponse> failure(
            HttpStatus httpStatus, SimpleErrorResponse data) {

        return ResponseEntity.status(httpStatus).body(data);
    }
}

