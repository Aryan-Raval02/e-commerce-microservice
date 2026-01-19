package com.example.cart.Dto.Response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonPropertyOrder({
        "cartId",
        "userId",
        "status",
        "cartItems"
})
public class CartResponse {

    private Long cartId;
    private UUID userId;
    private String status;
    private List<CartItemResponse> cartItems;

}
