package com.example.demo.service;

import com.example.demo.model.BundleRule;

import org.springframework.stereotype.Service;
@Service

public interface BundleRuleService {
    BundleRule createRule(BundleRule rule);
}