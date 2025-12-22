package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "discount_applications")
public class DiscountApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private BundleRule bundleRule;

    private BigDecimal discountAmount;

    private Timestamp appliedAt;

    @PrePersist
    public void onApply() {
        appliedAt = new Timestamp(System.currentTimeMillis());
    }

    // getters & setters
}
