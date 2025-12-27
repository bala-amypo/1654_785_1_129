package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class DiscountServiceImpl {

    private final CartRepository cartRepo;
    private final CartItemRepository itemRepo;
    private final BundleRuleRepository ruleRepo;
    private final DiscountApplicationRepository discountRepo;

    public DiscountServiceImpl(CartRepository c, CartItemRepository i,
                               BundleRuleRepository r, DiscountApplicationRepository d) {
        this.cartRepo = c;
        this.itemRepo = i;
        this.ruleRepo = r;
        this.discountRepo = d;
    }

    public List<DiscountApplication> evaluateDiscounts(Long cartId) {

        Cart cart = cartRepo.findById(cartId).orElseThrow();
        if (!cart.getActive()) return Collections.emptyList();

        discountRepo.deleteByCartId(cartId);

        List<CartItem> items = itemRepo.findByCartId(cartId);
        Set<Long> productIds = items.stream()
                .map(i -> i.getProduct().getId())
                .collect(Collectors.toSet());

        List<DiscountApplication> result = new ArrayList<>();

        for (BundleRule rule : ruleRepo.findByActiveTrue()) {
            Set<Long> required = Arrays.stream(rule.getRequiredProductIds().split(","))
                    .map(String::trim).map(Long::valueOf).collect(Collectors.toSet());

            if (productIds.containsAll(required)) {
                BigDecimal total = items.stream()
                        .map(i -> i.getProduct().getPrice()
                                .multiply(BigDecimal.valueOf(i.getQuantity())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                BigDecimal discount = total.multiply(
                        BigDecimal.valueOf(rule.getDiscountPercentage() / 100));

                DiscountApplication app = new DiscountApplication();
                app.setCart(cart);
                app.setBundleRule(rule);
                app.setDiscountAmount(discount);
                app.setAppliedAt(LocalDateTime.now());

                result.add(discountRepo.save(app));
            }
        }
        return result;
    }
}
