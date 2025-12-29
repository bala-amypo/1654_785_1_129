package com.example.demo.service.impl;

import com.example.demo.model.DiscountApplication;
import com.example.demo.service.DiscountService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Override
    public List<DiscountApplication> evaluateDiscounts(Long orderId) {

        List<DiscountApplication> list = new ArrayList<>();

        // ✅ orderId is Long (CORRECT)
        list.add(new DiscountApplication(orderId, 50.0));

        return list;
    }
}
