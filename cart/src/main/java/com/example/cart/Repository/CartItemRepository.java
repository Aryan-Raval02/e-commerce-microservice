package com.example.cart.Repository;

import com.example.cart.Models.Cart;
import com.example.cart.Models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCartAndProductId(Cart cart, Long productId);
}
