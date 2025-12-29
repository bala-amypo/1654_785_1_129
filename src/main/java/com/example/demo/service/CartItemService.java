package com.example.demo.service;

import com.example.demo.model.CartItem;
import java.util.List;

public interface CartItemService {
    CartItem addItem(Long cartId, Long productId, int quantity);
    List<CartItem> getItemsByCartId(Long cartId);
    void removeItem(Long itemId);
}
