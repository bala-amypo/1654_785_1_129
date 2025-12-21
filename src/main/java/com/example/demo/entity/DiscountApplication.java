package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
public class DiscountApplication {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private BundleRule bundleRule;

    private BigDecimal discountAmount;
    private Instant appliedAt;

    @PrePersist
    void applied() {
        appliedAt = Instant.now();
    }
}