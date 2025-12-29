package com.example.demo.service.impl;

import com.example.demo.model.BundleRule;
import com.example.demo.service.BundleRuleService;
import org.springframework.stereotype.Service;
import com.example.demo.service.BundleRuleService;

@Service
public class BundleRuleServiceImpl implements BundleRuleService {

    @Override
    public BundleRule createBundleRule(BundleRule rule) {
        return rule;
    }
}
