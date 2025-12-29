package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.service.CartItemService;

@RestController
@RequestMapping("/cart")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping("/add")
    public String addItem(
            @RequestParam Long productId,
            @RequestParam int quantity) {
        return cartItemService.addItemToCart(productId, quantity);
    }
}
