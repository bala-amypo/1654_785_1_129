package com.example.demo.service.impl;

import com.example.demo.model.DiscountApplication;
import com.example.demo.repository.DiscountApplicationRepository;
import com.example.demo.service.DiscountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountApplicationRepository discountApplicationRepository;

    public DiscountServiceImpl(DiscountApplicationRepository discountApplicationRepository) {
        this.discountApplicationRepository = discountApplicationRepository;
    }

    @Override
    public List<DiscountApplication> evaluateDiscounts(Long cartId) {
        return discountApplicationRepository.findByCartId(cartId);
    }

    @Override
    public DiscountApplication getApplicationById(Long id) {
        return discountApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public List<DiscountApplication> getApplicationsForCart(Long cartId) {
        return discountApplicationRepository.findByCartId(cartId);
    }
}
