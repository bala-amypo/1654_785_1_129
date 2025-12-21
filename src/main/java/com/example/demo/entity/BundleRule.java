package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class BundleRule {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String ruleName;

    private String requiredProductIds;
    private Double discountPercentage;
    private Boolean active = true;

    // getters & setters
}