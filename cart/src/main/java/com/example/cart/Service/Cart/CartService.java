package com.example.cart.Service.Cart;

import com.example.cart.Dto.Response.CartResponse;
import com.example.cart.Models.Cart;

import java.util.UUID;

public interface CartService {

    CartResponse getOrCreateCart(UUID userId);

    CartResponse addItem(UUID userId, Long productId, int quantity);

    void checkout(UUID userId);

    CartResponse updateItem(UUID userId, Long productId, int quantity);
}
