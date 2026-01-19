package com.example.order.Dto.Response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonPropertyOrder({
        "orderId",
        "orderNumber",
        "userId",
        "status",
        "totalAmount",
        "items"
})
public class OrderResponse {

    private Long orderId;
    private String orderNumber;
    private UUID userId;
    private BigDecimal totalAmount;
    private String status;
    private List<OrderItemResponse> items = new ArrayList<>();
}
