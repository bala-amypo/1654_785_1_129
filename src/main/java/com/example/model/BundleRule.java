package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class BundleRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double discountPercentage;
    private boolean active;

    @ElementCollection
    private List<Long> requiredProductIds;

    public double getDiscountPercentage() { return discountPercentage; }
    public boolean isActive() { return active; }
    public List<Long> getRequiredProductIds() { return requiredProductIds; }

    public void setActive(boolean active) { this.active = active; }
}
