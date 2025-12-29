package com.example.demo.model;

public class DiscountApplication {

    private String discountName;
    private double amount;

    public DiscountApplication() {}

    public DiscountApplication(String discountName, double amount) {
        this.discountName = discountName;
        this.amount = amount;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
