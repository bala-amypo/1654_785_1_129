package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.BundleRule;
import com.example.demo.repository.BundleRuleRepository;

import java.util.List;

public class BundleRuleService {

    private final BundleRuleRepository bundleRuleRepository;

    public BundleRuleService(BundleRuleRepository bundleRuleRepository) {
        this.bundleRuleRepository = bundleRuleRepository;
    }

    public BundleRule createRule(BundleRule rule) {
        if (rule.getDiscountPercentage() < 0 || rule.getDiscountPercentage() > 100) {
            throw new IllegalArgumentException("Invalid discount");
        }
        return bundleRuleRepository.save(rule);
    }

    public BundleRule getRuleById(Long id) {
        return bundleRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    public List<BundleRule> getActiveRules() {
        return bundleRuleRepository.findByActiveTrue();
    }

    public void deactivateRule(Long id) {
        BundleRule rule = getRuleById(id);
        rule.setActive(false);
        bundleRuleRepository.save(rule);
    }
}
