package com.example.demo.service;

import com.example.demo.model.CartItem;
import com.example.demo.repository.CartItemRepository;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {

    private final CartItemRepository repo;

    public CartItemService(CartItemRepository repo) {
        this.repo = repo;
    }

    public CartItem addItem(CartItem item) {
        if (item.getQuantity() <= 0)
            throw new IllegalArgumentException("Quantity must be positive");
        return repo.save(item);
    }
}