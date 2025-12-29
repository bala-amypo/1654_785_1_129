package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class CartItemServiceImpl {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public CartItemServiceImpl(CartRepository cartRepository,
                               ProductRepository productRepository,
                               CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public CartItem addItemToCart(CartItem item) {

        if (item.getQuantity() <= 0)
            throw new IllegalArgumentException("Quantity must be positive");

        Cart cart = cartRepository.findById(item.getCart().getId())
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        if (!cart.getActive())
            throw new IllegalArgumentException("Only active carts allowed");

        Product product = productRepository.findById(item.getProduct().getId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        CartItem existing = cartItemRepository
                .findByCartIdAndProductId(cart.getId(), product.getId())
                .orElse(null);

        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + item.getQuantity());
            return cartItemRepository.save(existing);
        }

        item.setCart(cart);
        item.setProduct(product);
        return cartItemRepository.save(item);
    }

    public List<CartItem> getItemsForCart(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }
}
