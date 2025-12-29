package com.example.demo.service.impl;

import com.example.demo.model.DiscountApplication;
import com.example.demo.service.DiscountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Override
    public List<DiscountApplication> getApplicationsForCart(Long cartId) {
        return List.of(new DiscountApplication("NEWUSER", 100.0));
    }
}
