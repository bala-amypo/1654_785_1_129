package com.example.demo.service;

import com.example.demo.model.CartItem;
import java.util.List;

import org.springframework.stereotype.Service;
@Service
public interface CartItemService {
    CartItem addItemToCart(CartItem item);
    List<CartItem> getItemsForCart(Long cartId);
}