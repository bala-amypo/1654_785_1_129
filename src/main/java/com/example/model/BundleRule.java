package com.example.demo.model;

import java.util.List;

public class BundleRule {

    private List<Long> requiredProductIds;
    private double discountPercentage;
    private boolean active;

    public List<Long> getRequiredProductIds() {
        return requiredProductIds;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public boolean isActive() {
        return active;
    }

    public void setRequiredProductIds(List<Long> ids) {
        this.requiredProductIds = ids;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
