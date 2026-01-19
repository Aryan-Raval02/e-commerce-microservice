package com.example.order.Dto.Request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {

    private Long productId;
    private Integer quantity;
    private BigDecimal price;
}
