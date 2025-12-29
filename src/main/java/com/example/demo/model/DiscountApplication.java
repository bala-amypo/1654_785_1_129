package com.example.demo.model;

public class DiscountApplication {

    private String discountName;
    private double discountAmount;

    public DiscountApplication() {}

    public DiscountApplication(String discountName, double discountAmount) {
        this.discountName = discountName;
        this.discountAmount = discountAmount;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }
}
