package com.example.order.Dto.Request;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private UUID userId;
    private BigDecimal totalAmount;
    private List<OrderItemRequest> items;
}
