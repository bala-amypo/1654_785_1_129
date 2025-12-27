package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
@Service
public class CartItemServiceImpl {

    private final CartRepository cartRepo;
    private final ProductRepository productRepo;
    private final CartItemRepository cartItemRepo;

    public CartItemServiceImpl(CartRepository c, ProductRepository p, CartItemRepository ci) {
        this.cartRepo = c;
        this.productRepo = p;
        this.cartItemRepo = ci;
    }

    public CartItem addItemToCart(CartItem item) {

        Cart cart = cartRepo.findById(item.getCart().getId()).orElseThrow();
        if (!cart.getActive())
            throw new IllegalArgumentException("Only active carts allowed");

        if (item.getQuantity() <= 0)
            throw new IllegalArgumentException("Quantity must be positive");

        Product product = productRepo.findById(item.getProduct().getId()).orElseThrow();

        return cartItemRepo.findByCartIdAndProductId(cart.getId(), product.getId())
                .map(existing -> {
                    existing.setQuantity(existing.getQuantity() + item.getQuantity());
                    return cartItemRepo.save(existing);
                })
                .orElseGet(() -> cartItemRepo.save(item));
    }

    public java.util.List<CartItem> getItemsForCart(Long cartId) {
        return cartItemRepo.findByCartId(cartId);
    }
}
