package com.example.demo.service;

import com.example.demo.repository.DiscountApplicationRepository;
import com.example.demo.model.DiscountApplication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DiscountService {

    private final DiscountApplicationRepository repository;

    public DiscountService(DiscountApplicationRepository repository) {
        this.repository = repository;
    }

    public void applyDiscount(DiscountApplication da, BigDecimal amount) {
        da.setDiscountAmount(amount);
        repository.save(da);
    }
}
