package com.example.order.Dto.Response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@JsonPropertyOrder({
        "orderItemId",
        "productId",
        "quantity",
        "price"
})
public class OrderItemResponse {

    private Long orderItemId;
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
}
