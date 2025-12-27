package com.example.demo.service.impl;

import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepository;
import jakarta.persistence.EntityNotFoundException;

public class CartServiceImpl {

    private final CartRepository repo;

    public CartServiceImpl(CartRepository repo) {
        this.repo = repo;
    }

    public Cart createCart(Long userId) {
        if (repo.findByUserIdAndActiveTrue(userId).isPresent())
            throw new IllegalArgumentException("Active cart already exists");

        Cart c = new Cart();
        c.setUserId(userId);
        return repo.save(c);
    }

    public Cart getActiveCartForUser(Long userId) {
        return repo.findByUserIdAndActiveTrue(userId)
                .orElseThrow(() -> new EntityNotFoundException("Active cart not found"));
    }
}
