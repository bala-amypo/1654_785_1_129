package com.example.demo.controller;

import com.example.demo.model.CartItem;
import com.example.demo.service.CartItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    private final CartItemService cartItemService;

    // Constructor Injection
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    // Add item to cart
    @PostMapping
    public CartItem addItemToCart(@RequestBody CartItem cartItem) {
        return cartItemService.addItemToCart(cartItem);
    }

    // Get all items for a cart
    @GetMapping("/cart/{cartId}")
    public List<CartItem> getItemsForCart(@PathVariable Long cartId) {
        return cartItemService.getItemsForCart(cartId);
    }
}
