package com.example.order.Service.Order;

import com.example.order.Dto.Request.OrderItemRequest;
import com.example.order.Dto.Request.OrderRequest;
import com.example.order.Dto.Response.OrderItemResponse;
import com.example.order.Dto.Response.OrderResponse;
import com.example.order.Exception.ResourceNotFoundException;
import com.example.order.Models.Order;
import com.example.order.Models.OrderItem;
import com.example.order.Repository.OrderItemRepository;
import com.example.order.Repository.OrderRepository;
import com.example.order.Utility.ResultDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public ResultDto<OrderResponse> getOrdersByUser(UUID userId) {
        List<OrderResponse> list = new ArrayList<>();

        for(Order order : orderRepository.findByUserId(userId)){
            list.add(toResponse(order));
        }

        ResultDto<OrderResponse> result = new ResultDto<>();

        result.setList(list);
        result.setTotal(list.size());

        return result;
    }


    private OrderResponse toResponse(Order order){
        OrderResponse response = new OrderResponse();

        response.setOrderId(order.getOrderId());
        response.setOrderNumber(order.getOrderNumber());
        response.setUserId(order.getUserId());
        response.setTotalAmount(order.getTotalAmount());
        response.setStatus(order.getStatus());

        List<OrderItemResponse> orderItems = new ArrayList<>();
        for(OrderItem orderItem : order.getItems()){
            OrderItemResponse itemResponse = new OrderItemResponse();

            itemResponse.setOrderItemId(orderItem.getOrderItemId());
            itemResponse.setProductId(orderItem.getProductId());
            itemResponse.setPrice(orderItem.getPrice());
            itemResponse.setQuantity(orderItem.getQuantity());

            orderItems.add(itemResponse);
        }

        response.setItems(orderItems);

        return response;
    }

    @Override
    public OrderResponse getByOrderNumber(String orderNumber) {
        Order order = orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Order Not Found for OrderNumber !!"));

        return toResponse(order);
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order = new Order();

        order.setUserId(orderRequest.getUserId());
        order.setTotalAmount(orderRequest.getTotalAmount());

        Order saved = orderRepository.save(order);

        for(OrderItemRequest request : orderRequest.getItems()){
            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(saved);
            orderItem.setProductId(request.getProductId());
            orderItem.setQuantity(request.getQuantity());
            orderItem.setPrice(request.getPrice());

            orderItemRepository.save(orderItem);
        }

        return toResponse(saved);
    }
}
