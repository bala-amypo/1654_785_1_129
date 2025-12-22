package com.example.demo.service;

import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository repo;

    public CartService(CartRepository repo) {
        this.repo = repo;
    }

    public Cart createCart(Long userId) {
        if (repo.findByUserId(userId) != null)
            throw new IllegalArgumentException("Cart already exists");
        Cart cart = new Cart();
        cart.setUserId(userId);
        return repo.save(cart);
    }
}