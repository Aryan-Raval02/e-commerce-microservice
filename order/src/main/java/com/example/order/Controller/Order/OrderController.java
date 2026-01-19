package com.example.order.Controller.Order;

import com.example.order.Dto.Request.OrderRequest;
import com.example.order.Dto.Response.OrderResponse;
import com.example.order.Service.Order.OrderService;
import com.example.order.Utility.ResponseBuilder;
import com.example.order.Utility.ResponseStructure;
import com.example.order.Utility.ResultDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<ResponseStructure<ResultDto<OrderResponse>>> getOrdersByUser(@RequestHeader("User-Id") UUID userId){
        return ResponseBuilder.success(HttpStatus.OK, "All Orders of User !!", orderService.getOrdersByUser(userId));
    }

    @GetMapping("/{orderNumber}")
    public ResponseEntity<ResponseStructure<OrderResponse>> getOrderByOrderNumber(@PathVariable String orderNumber){
        return ResponseBuilder.success(HttpStatus.OK, "Order Details !!", orderService.getByOrderNumber(orderNumber));
    }

    @PostMapping
    public ResponseEntity<ResponseStructure<OrderResponse>> createOrder(@RequestBody OrderRequest orderRequest){
        return ResponseBuilder.success(HttpStatus.CREATED, "Order Created !!", orderService.createOrder(orderRequest));
    }
}
