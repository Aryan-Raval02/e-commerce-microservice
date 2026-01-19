package com.example.cart.Utility;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonPropertyOrder({"status","message","data"})
public class ResponseStructure<T> {
    private int status;
    private String message;
    private T data;
}
