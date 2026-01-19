package com.example.cart.Controller;

import com.example.cart.Dto.Response.CartResponse;
import com.example.cart.Models.Cart;
import com.example.cart.Service.Cart.CartService;
import com.example.cart.Utility.ResponseBuilder;
import com.example.cart.Utility.ResponseStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<ResponseStructure<CartResponse>> getCartDetails(@RequestHeader("User-Id") UUID userId){
        return ResponseBuilder.success(HttpStatus.OK, "Cart Details !!", cartService.getOrCreateCart(userId));
    }

    @PostMapping("/items")
    public ResponseEntity<ResponseStructure<CartResponse>> addItem(
            @RequestHeader("User-Id") UUID userId,
            @RequestParam Long productId,
            @RequestParam int quantity) {

        return ResponseBuilder.success(HttpStatus.CREATED, "Cart Item Added !!", cartService.addItem(userId, productId, quantity));
    }

    @PostMapping("/checkout")
    public void checkout(@RequestHeader("User-Id") UUID userId) {
        cartService.checkout(userId);
    }

    @PutMapping("/items")
    public ResponseEntity<ResponseStructure<CartResponse>>  updateItem(
            @RequestHeader("User-Id") UUID userId,
            @RequestParam Long productId,
            @RequestParam int quantity) {

        return ResponseBuilder.success(HttpStatus.CREATED, "Cart Item Added !!", cartService.updateItem(userId, productId, quantity));
    }
}
