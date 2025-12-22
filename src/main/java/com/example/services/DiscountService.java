package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DiscountService {

    private final BundleRuleRepository bundleRepo;
    private final CartItemRepository itemRepo;
    private final DiscountApplicationRepository discountRepo;

    public DiscountService(BundleRuleRepository b, CartItemRepository i, DiscountApplicationRepository d) {
        this.bundleRepo = b;
        this.itemRepo = i;
        this.discountRepo = d;
    }

    public void evaluateDiscounts(Long cartId) {
        List<BundleRule> rules = bundleRepo.findByActiveTrue();
        for (BundleRule rule : rules) {
            DiscountApplication app = new DiscountApplication();
            app.setDiscountAmount(BigDecimal.TEN);
            discountRepo.save(app);
        }
    }
}