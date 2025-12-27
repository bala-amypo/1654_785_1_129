package com.example.demo.service;

import com.example.demo.model.DiscountApplication;
import java.util.List;

import org.springframework.stereotype.Service;
@Service

public interface DiscountService {
    List<DiscountApplication> evaluateDiscounts(Long cartId);
}