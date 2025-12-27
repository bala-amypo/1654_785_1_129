package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DiscountService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class DiscountServiceImpl implements DiscountService {
    private DiscountApplicationRepository discountApplicationRepository;
    private BundleRuleRepository bundleRuleRepository;
    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;

    public List<DiscountApplication> evaluateDiscounts(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null || !cart.getActive()) {
            return Collections.emptyList();
        }

        discountApplicationRepository.deleteByCartId(cartId);
        
        List<CartItem> cartItems = cartItemRepository.findByCartId(cartId);
        List<BundleRule> rules = bundleRuleRepository.findByActiveTrue();
        List<DiscountApplication> applications = new ArrayList<>();

        for (BundleRule rule : rules) {
            Set<Long> requiredIds = Arrays.stream(rule.getRequiredProductIds().split(","))
                .map(String::trim)
                .map(Long::valueOf)
                .collect(Collectors.toSet());

            Set<Long> cartProductIds = cartItems.stream()
                .map(item -> item.getProduct().getId())
                .collect(Collectors.toSet());

            if (cartProductIds.containsAll(requiredIds)) {
                BigDecimal totalAmount = cartItems.stream()
                    .filter(item -> requiredIds.contains(item.getProduct().getId()))
                    .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

                BigDecimal discountAmount = totalAmount.multiply(BigDecimal.valueOf(rule.getDiscountPercentage() / 100));

                DiscountApplication app = new DiscountApplication();
                app.setCart(cart);
                app.setBundleRule(rule);
                app.setDiscountAmount(discountAmount);
                app.setAppliedAt(LocalDateTime.now());
                
                applications.add(discountApplicationRepository.save(app));
            }
        }

        return applications;
    }
}