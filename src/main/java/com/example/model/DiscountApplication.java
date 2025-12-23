package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class DiscountApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double discountAmount;
}
