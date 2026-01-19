package com.example.cart.Utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomFieldError {
    private String field;
    private String message;
    private Object rejectedValue;
}
