package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Override
    public String addItemToCart(Long productId, int quantity) {
        return "Item added successfully";
    }
}
