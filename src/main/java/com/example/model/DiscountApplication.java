package com.example.demo.model;

import java.math.BigDecimal;

public class DiscountApplication {

    private BundleRule bundleRule;
    private BigDecimal discountAmount;

    public BundleRule getBundleRule() {
        return bundleRule;
    }

    public void setBundleRule(BundleRule bundleRule) {
        this.bundleRule = bundleRule;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }
}
