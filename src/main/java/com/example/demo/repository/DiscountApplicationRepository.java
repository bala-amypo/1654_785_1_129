package com.example.demo.repository;

import com.example.demo.model.DiscountApplication;
import java.util.List;

public interface DiscountApplicationRepository {
    void deleteByCartId(Long cartId);
    DiscountApplication save(DiscountApplication discountApplication);
    List<DiscountApplication> findByCartId(Long cartId);
}