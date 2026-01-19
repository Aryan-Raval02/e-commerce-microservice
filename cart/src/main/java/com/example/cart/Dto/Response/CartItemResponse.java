package com.example.cart.Dto.Response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({
        "cartItemId",
        "productId",
        "quantity"
})
public class CartItemResponse {
    private Long cartItemId;
    private Long productId;
    private Integer quantity;
}
