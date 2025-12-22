package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import com.example.demo.model.DiscountApplication;
import com.example.demo.repository.DiscountApplicationRepository;

@Service
public class DiscountService {

    private final DiscountApplicationRepository repo;

    public DiscountService(DiscountApplicationRepository repo) {
        this.repo = repo;
    }

    public void saveDiscount(BigDecimal amount) {
        DiscountApplication da = new DiscountApplication();
        da.setDiscountAmount(amount);
        repo.save(da);
    }
}
