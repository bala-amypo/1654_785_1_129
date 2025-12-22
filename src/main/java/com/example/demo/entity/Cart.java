package com.example.demo.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class Cart {

    @Id @GeneratedValue
    private Long id;

    private Long userId;

    private Instant createdAt;
    private Instant updatedAt;

    @PrePersist
    void create() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    void update() {
        updatedAt = Instant.now();
    }
}