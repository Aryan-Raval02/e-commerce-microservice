package com.example.cart.Service.Cart;

import com.example.cart.Dto.Response.CartItemResponse;
import com.example.cart.Dto.Response.CartResponse;
import com.example.cart.Exception.ResourceNotFoundException;
import com.example.cart.Models.Cart;
import com.example.cart.Models.CartItem;
import com.example.cart.Models.Product;
import com.example.cart.Repository.CartItemRepository;
import com.example.cart.Repository.CartRepository;
import com.example.cart.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    @Override
    public CartResponse getOrCreateCart(UUID userId) {
        Cart saved = createCartWithUserOrGet(userId);
        return toResponse(saved);
    }

    @Override
    public CartResponse addItem(UUID userId, Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found !!"));

        Cart cart = createCartWithUserOrGet(userId);

        CartItem cartItem = cartItemRepository.findByCartAndProductId(cart, productId)
                .orElseGet(() -> {
                    CartItem ci = new CartItem();

                    ci.setCart(cart);
                    ci.setProductId(productId);
                    ci.setQuantity(0);

                    return ci;
                });

        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);

        return toResponse(cart);
    }

    private CartResponse toResponse(Cart cart) {

        CartResponse cartResponse = new CartResponse();

        cartResponse.setCartId(cart.getCartId());
        cartResponse.setUserId(cart.getUserId());
        cartResponse.setStatus(cart.getStatus());

        List<CartItemResponse> list = new ArrayList<>();
        for(CartItem cartItem : cart.getItems()){
            CartItemResponse response = new CartItemResponse();

            response.setCartItemId(cartItem.getCartItemId());
            response.setProductId(cartItem.getProductId());
            response.setQuantity(cartItem.getQuantity());

            list.add(response);
        }
        cartResponse.setCartItems(list);

        return cartResponse;
    }

    private Cart createCartWithUserOrGet(UUID userId){
        Cart cart = new Cart();

        cart.setUserId(userId);
        cart.setStatus("ACTIVE");
        cart.setItems(new ArrayList<>());

        return cartRepository.findByUserIdAndStatus(userId, "ACTIVE")
                .orElseGet(() -> cartRepository.save(cart));
    }

    @Override
    public void checkout(UUID userId) {
        Cart cart = createCartWithUserOrGet(userId);

        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        // 1. Call Product Service → get prices
        // 2. Call Inventory Service → reserve stock
        // 3. Create Order (sync or event)
        // 4. Mark cart as CHECKED_OUT

        cart.setStatus("CHECKED_OUT");
        cartRepository.save(cart);
    }

    @Override
    public CartResponse updateItem(UUID userId, Long productId, int quantity) {
        Cart cart = createCartWithUserOrGet(userId);

        CartItem cartItem = cartItemRepository.findByCartAndProductId(cart, productId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart Item Not Found !!"));

        if (quantity <= 0) {
            cartItemRepository.delete(cartItem);
        } else {
            cartItem.setQuantity(quantity);
        }

        return toResponse(cart);
    }
}
