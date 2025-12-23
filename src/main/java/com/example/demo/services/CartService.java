package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepository;

@Service
public class CartService {

    private final CartRepository repo;

    public CartService(CartRepository repo) {
        this.repo = repo;
    }

    public Cart getCartByUserId(Long userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        return repo.save(cart);
    }
}
