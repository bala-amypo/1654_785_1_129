package com.example.demo.service;

import com.example.demo.model.Product;

import org.springframework.stereotype.Service;
@Service

public interface ProductService {
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    Product getProductById(Long id);
    void deactivateProduct(Long id);
}