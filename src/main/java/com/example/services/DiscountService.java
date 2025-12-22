package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DiscountService {

    private final BundleRuleRepository bundleRuleRepository;
    private final CartItemRepository cartItemRepository;
    private final DiscountApplicationRepository discountApplicationRepository;

    public DiscountService(BundleRuleRepository bundleRuleRepository,
                           CartItemRepository cartItemRepository,
                           DiscountApplicationRepository discountApplicationRepository) {
        this.bundleRuleRepository = bundleRuleRepository;
        this.cartItemRepository = cartItemRepository;
        this.discountApplicationRepository = discountApplicationRepository;
    }

    public void evaluateDiscounts(Long cartId) {
        List<CartItem> items = cartItemRepository.findByCartId(cartId);

        Set<Long> productIds = items.stream()
                .map(i -> i.getProduct().getId())
                .collect(Collectors.toSet());

        for (BundleRule rule : bundleRuleRepository.findByActiveTrue()) {
            Set<Long> requiredIds = Set.of(rule.getRequiredProductIds().split(","))
                    .stream().map(Long::valueOf).collect(Collectors.toSet());

            if (productIds.containsAll(requiredIds)) {
                BigDecimal total = items.stream()
                        .map(i -> i.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(i.getQuantity())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                BigDecimal discount = total
                        .multiply(BigDecimal.valueOf(rule.getDiscountPercentage()))
                        .divide(BigDecimal.valueOf(100));

                DiscountApplication da = new DiscountApplication();
                da.setBundleRule(rule);
                da.setDiscountAmount(discount);

                discountApplicationRepository.save(da);
            }
        }
    }

    public List<DiscountApplication> getApplicationsForCart(Long cartId) {
        return discountApplicationRepository.findByCartId(cartId);
    }
}
