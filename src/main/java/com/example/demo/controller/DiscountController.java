package com.example.demo.controller;

import com.example.demo.model.DiscountApplication;
import com.example.demo.service.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<List<DiscountApplication>> getDiscounts(
            @PathVariable Long cartId) {

        return ResponseEntity.ok(
                discountService.getApplicationsForCart(cartId)
        );
    }
}
