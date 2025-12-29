package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import com.example.demo.service.DiscountService;
@Service
public class DiscountServiceImpl implements DiscountService{

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final BundleRuleRepository bundleRuleRepository;
    private final DiscountApplicationRepository discountApplicationRepository;

    public DiscountServiceImpl(CartRepository cartRepository,
                               CartItemRepository cartItemRepository,
                               BundleRuleRepository bundleRuleRepository,
                               DiscountApplicationRepository discountApplicationRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.bundleRuleRepository = bundleRuleRepository;
        this.discountApplicationRepository = discountApplicationRepository;
    }

    public List<DiscountApplication> evaluateDiscounts(Long cartId) {

        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null || !cart.getActive())
            return Collections.emptyList();

        discountApplicationRepository.deleteByCartId(cartId);

        List<CartItem> items = cartItemRepository.findByCartId(cartId);
        Set<Long> productIds = new HashSet<>();
        items.forEach(ci -> productIds.add(ci.getProduct().getId()));

        List<DiscountApplication> result = new ArrayList<>();

        for (BundleRule rule : bundleRuleRepository.findByActiveTrue()) {
            Set<Long> required = new HashSet<>();
            for (String s : rule.getRequiredProductIds().split(",")) {
                required.add(Long.parseLong(s.trim()));
            }

            if (productIds.containsAll(required)) {
                BigDecimal total = BigDecimal.ZERO;
                for (CartItem ci : items) {
                    if (required.contains(ci.getProduct().getId())) {
                        total = total.add(
                                ci.getProduct().getPrice()
                                        .multiply(BigDecimal.valueOf(ci.getQuantity()))
                        );
                    }
                }

                BigDecimal discount = total
                        .multiply(BigDecimal.valueOf(rule.getDiscountPercentage()))
                        .divide(BigDecimal.valueOf(100));

                DiscountApplication app = new DiscountApplication();
                app.setCart(cart);
                app.setBundleRule(rule);
                app.setDiscountAmount(discount);
                app.setAppliedAt(LocalDateTime.now());

                result.add(discountApplicationRepository.save(app));
            }
        }

        return result;
    }
}
