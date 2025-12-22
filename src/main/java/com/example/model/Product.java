package com.example.demo.model;

public class Product {

    private Long id;
    private String sku;
    private String name;
    private String category;
    private double price;

    public Long getId() { return id; }
    public String getSku() { return sku; }

    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setPrice(double price) { this.price = price; }
}
