package com.example.cart.Utility;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@JsonPropertyOrder({"status", "message", "timeStamp"})
public class SimpleErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timeStamp;
}
