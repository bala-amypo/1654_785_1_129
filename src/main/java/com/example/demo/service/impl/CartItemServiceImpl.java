package com.example.demo.service.impl;

import com.example.demo.model.CartItem;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.service.CartItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository repository;

    public CartItemServiceImpl(CartItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public CartItem addItemToCart(CartItem item) {
        return repository.save(item);
    }

    @Override
    public List<CartItem> getItemsForCart(Long cartId) {
        return repository.findByCartId(cartId);
    }
}
