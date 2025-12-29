package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class DiscountApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private Double discountAmount;

    public DiscountApplication() {}

    public DiscountApplication(Long orderId, Double discountAmount) {
        this.orderId = orderId;
        this.discountAmount = discountAmount;
    }

    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }
}
