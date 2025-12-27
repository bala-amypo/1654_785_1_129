package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository repo;

    public CartService(CartRepository repo) {
        this.repo = repo;
    }

    public Cart createCart(Long userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        return repo.save(cart);
    }

    public Cart getActiveCartForUser(Long userId) {
        return repo.findByUserIdAndActiveTrue(userId)
            .orElseThrow(() -> new EntityNotFoundException("Active cart not found"));
    }

    public Cart getCartByUserId(Long userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        return repo.save(cart);
    }
}