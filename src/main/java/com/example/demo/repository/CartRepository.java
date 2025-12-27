package com.example.demo.repository;

import com.example.demo.model.Cart;
import java.util.Optional;

public interface CartRepository extends JpaRepository{
    Optional<Cart> findByUserIdAndActiveTrue(Long userId);
    Cart save(Cart cart);
    Optional<Cart> findById(Long id);
}