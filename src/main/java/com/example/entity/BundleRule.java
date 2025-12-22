package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bundle_rules", uniqueConstraints = @UniqueConstraint(columnNames = "ruleName"))
public class BundleRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;

    @Column(length = 500)
    private String requiredProductIds; // CSV

    private Double discountPercentage;

    private Boolean active = true;

    // getters & setters
}
