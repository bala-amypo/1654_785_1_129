package com.example.demo.repositories;

import com.example.demo.model.DiscountApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountApplicationRepository
        extends JpaRepository<DiscountApplication, Long> {
}
