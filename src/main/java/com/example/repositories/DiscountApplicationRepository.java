package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.DiscountApplication;

public interface DiscountApplicationRepository
        extends JpaRepository<DiscountApplication, Long> {
}
