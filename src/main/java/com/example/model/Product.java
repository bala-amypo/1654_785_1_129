package com.example.demo.model;

public class Product {
    private Long id;
    private String name;
    private String category;
    private double price;
    private boolean active;

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
