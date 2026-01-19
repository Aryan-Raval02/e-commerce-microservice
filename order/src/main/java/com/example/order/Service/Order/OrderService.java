package com.example.order.Service.Order;

import com.example.order.Dto.Request.OrderRequest;
import com.example.order.Dto.Response.OrderResponse;
import com.example.order.Utility.ResultDto;

import java.util.UUID;

public interface OrderService {
    ResultDto<OrderResponse> getOrdersByUser(UUID userId);

    OrderResponse getByOrderNumber(String orderNumber);

    OrderResponse createOrder(OrderRequest orderRequest);
}
