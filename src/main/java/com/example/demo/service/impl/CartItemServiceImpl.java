package com.example.demo.service.impl;

import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.CartItemService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    private CartItemRepository cartItemRepository;
    private CartRepository cartRepository;
    private ProductRepository productRepository;

    public CartItem addItemToCart(CartItem item) {
        if (item.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        
        Cart cart = cartRepository.findById(item.getCart().getId()).orElse(null);
        if (cart != null && !cart.getActive()) {
            throw new IllegalArgumentException("Cannot add items to inactive carts");
        }

        Optional<CartItem> existing = cartItemRepository.findByCartIdAndProductId(
            item.getCart().getId(), item.getProduct().getId());
        
        if (existing.isPresent()) {
            CartItem existingItem = existing.get();
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
            return cartItemRepository.save(existingItem);
        }
        
        return cartItemRepository.save(item);
    }

    public List<CartItem> getItemsForCart(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }
}