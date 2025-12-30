// package com.example.demo.service.impl;

// import com.example.demo.model.*;
// import com.example.demo.repository.*;
// import org.springframework.stereotype.Service;
// import java.math.BigDecimal;
// import java.time.LocalDateTime;
// import java.util.*;
// import com.example.demo.service.DiscountService;
// @Service
// public class DiscountServiceImpl implements DiscountService{

//     private final CartRepository cartRepository;
//     private final CartItemRepository cartItemRepository;
//     private final BundleRuleRepository bundleRuleRepository;
//     private final DiscountApplicationRepository discountApplicationRepository;

//     public DiscountServiceImpl(CartRepository cartRepository,
//                                CartItemRepository cartItemRepository,
//                                BundleRuleRepository bundleRuleRepository,
//                                DiscountApplicationRepository discountApplicationRepository) {
//         this.cartRepository = cartRepository;
//         this.cartItemRepository = cartItemRepository;
//         this.bundleRuleRepository = bundleRuleRepository;
//         this.discountApplicationRepository = discountApplicationRepository;
//     }

//     public List<DiscountApplication> getApplicationsForCart(Long cartId) {

//         Cart cart = cartRepository.findById(cartId).orElse(null);
//         if (cart == null || !cart.getActive())
//             return Collections.emptyList();

//         discountApplicationRepository.deleteByCartId(cartId);

//         List<CartItem> items = cartItemRepository.findByCartId(cartId);
//         Set<Long> productIds = new HashSet<>();
//         items.forEach(ci -> productIds.add(ci.getProduct().getId()));

//         List<DiscountApplication> result = new ArrayList<>();

//         for (BundleRule rule : bundleRuleRepository.findByActiveTrue()) {
//             Set<Long> required = new HashSet<>();
//             for (String s : rule.getRequiredProductIds().split(",")) {
//                 required.add(Long.parseLong(s.trim()));
//             }

//             if (productIds.containsAll(required)) {
//                 BigDecimal total = BigDecimal.ZERO;
//                 for (CartItem ci : items) {
//                     if (required.contains(ci.getProduct().getId())) {
//                         total = total.add(
//                                 ci.getProduct().getPrice()
//                                         .multiply(BigDecimal.valueOf(ci.getQuantity()))
//                         );
//                     }
//                 }

//                 BigDecimal discount = total
//                         .multiply(BigDecimal.valueOf(rule.getDiscountPercentage()))
//                         .divide(BigDecimal.valueOf(100));

//                 DiscountApplication app = new DiscountApplication();
//                 app.setCart(cart);
//                 app.setBundleRule(rule);
//                 app.setDiscountAmount(discount);
//                 app.setAppliedAt(LocalDateTime.now());

//                 result.add(discountApplicationRepository.save(app));
//             }
//         }

//         return result;
//     }
// }
package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DiscountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class DiscountServiceImpl implements DiscountService {

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

    /**
     * evaluateDiscounts(cartId)
     */
    @Override
    public List<DiscountApplication> evaluateDiscounts(Long cartId) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        if (!cart.getActive()) {
            return Collections.emptyList();
        }

        // Clear old discounts
        discountApplicationRepository.deleteByCartId(cartId);

        List<CartItem> cartItems = cartItemRepository.findByCartId(cartId);

        Set<Long> cartProductIds = new HashSet<>();
        for (CartItem item : cartItems) {
            cartProductIds.add(item.getProduct().getId());
        }

        List<DiscountApplication> applications = new ArrayList<>();

        // Fetch active bundles
        List<BundleRule> activeBundles = bundleRuleRepository.findByActiveTrue();

        for (BundleRule rule : activeBundles) {

            Set<Long> requiredProductIds = new HashSet<>();
            for (String id : rule.getRequiredProductIds().split(",")) {
                requiredProductIds.add(Long.parseLong(id.trim()));
            }

            // Check required products
            if (cartProductIds.containsAll(requiredProductIds)) {

                BigDecimal totalAmount = BigDecimal.ZERO;

                for (CartItem item : cartItems) {
                    if (requiredProductIds.contains(item.getProduct().getId())) {
                        totalAmount = totalAmount.add(
                                item.getProduct().getPrice()
                                        .multiply(BigDecimal.valueOf(item.getQuantity()))
                        );
                    }
                }

                // Calculate discount
                BigDecimal discountAmount = totalAmount
                        .multiply(BigDecimal.valueOf(rule.getDiscountPercentage()))
                        .divide(BigDecimal.valueOf(100));

                DiscountApplication application = new DiscountApplication();
                application.setCart(cart);
                application.setBundleRule(rule);
                application.setDiscountAmount(discountAmount);
                application.setAppliedAt(LocalDateTime.now());

                applications.add(discountApplicationRepository.save(application));
            }
        }

        return applications;
    }

    /**
     * getApplicationById(id)
     */
    @Override
    public DiscountApplication getApplicationById(Long id) {
        return discountApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discount Application not found"));
    }

    /**
     * getApplicationsForCart(cartId)
     */
    @Override
    public List<DiscountApplication> getApplicationsForCart(Long cartId) {
        return discountApplicationRepository.findByCartId(cartId);
    }
}
