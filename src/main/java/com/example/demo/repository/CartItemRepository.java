package com.example.demo.repository;

import com.example.demo.model.CartItem;
import java.util.List;
import java.util.Optional;

public interface CartItemRepository {
    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);
    CartItem save(CartItem cartItem);
    List<CartItem> findByCartId(Long cartId);
    List<CartItem> findByCartIdAndMinQuantity(Long cartId, Integer minQuantity);
}