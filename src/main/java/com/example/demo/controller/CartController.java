package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestParam Long userId) {
        return ResponseEntity.ok(cartService.createCart(userId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Cart> getActiveCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getActiveCartForUser(userId));
    }
}