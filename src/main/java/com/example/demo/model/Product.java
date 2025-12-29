package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Product {
    private String category;

    public String getCategory() {
        return category;
    }
}
