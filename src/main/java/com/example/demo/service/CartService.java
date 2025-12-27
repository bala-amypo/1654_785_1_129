package com.example.demo.service;

import com.example.demo.model.Cart;

import org.springframework.stereotype.Service;
@Service

public interface CartService {
    Cart createCart(Long userId);
    Cart getActiveCartForUser(Long userId);
    Cart getCartByUserId(Long userId);
}