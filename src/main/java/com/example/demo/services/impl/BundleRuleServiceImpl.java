package com.example.demo.service.impl;

import com.example.demo.model.BundleRule;
import com.example.demo.service.BundleRuleService;

public class BundleRuleServiceImpl implements BundleRuleService {
    public BundleRule createRule(BundleRule rule) {
        if (rule.getDiscountPercentage() < 0 || rule.getDiscountPercentage() > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100");
        }
        if (rule.getRequiredProductIds() == null || rule.getRequiredProductIds().trim().isEmpty()) {
            throw new IllegalArgumentException("Required product IDs cannot be empty");
        }
        return rule;
    }
}