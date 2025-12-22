package com.example.demo.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.math.BigDecimal;
import com.example.demo.model.*;
import com.example.demo.repository.*;

@Service
@RequiredArgsConstructor
public class DiscountService {

    private final DiscountApplicationRepository discountRepo;

    public void applyDiscount(BundleRule rule) {
        DiscountApplication da = new DiscountApplication();
        da.setBundleRule(rule);
        da.setDiscountAmount(BigDecimal.valueOf(rule.getDiscountPercentage()));
        discountRepo.save(da);
    }
}
