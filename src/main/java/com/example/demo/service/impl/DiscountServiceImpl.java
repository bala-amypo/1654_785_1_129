package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DiscountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final BundleRuleRepository bundleRuleRepository;
    private final CartItemRepository cartItemRepository;
    private final DiscountApplicationRepository discountApplicationRepository;

    // ✅ Constructor Injection
    public DiscountServiceImpl(
            BundleRuleRepository bundleRuleRepository,
            CartItemRepository cartItemRepository,
            DiscountApplicationRepository discountApplicationRepository) {
        this.bundleRuleRepository = bundleRuleRepository;
        this.cartItemRepository = cartItemRepository;
        this.discountApplicationRepository = discountApplicationRepository;
    }

    @Override
    public BigDecimal evaluateDiscounts(Long cartId) {

        List<CartItem> items = cartItemRepository.findByCartId(cartId);
        List<BundleRule> rules = bundleRuleRepository.findByActiveTrue();

        BigDecimal totalDiscount = BigDecimal.ZERO;

        for (BundleRule rule : rules) {
            BigDecimal discount = BigDecimal.valueOf(rule.getDiscountPercentage());
            totalDiscount = totalDiscount.add(discount);

            DiscountApplication app = new DiscountApplication();
            app.setDiscountAmount(discount);
            app.setAppliedAt(LocalDateTime.now());

            discountApplicationRepository.save(app);
        }

        return totalDiscount;
    }
}
