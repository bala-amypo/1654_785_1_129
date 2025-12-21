package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public Product createProduct(Product product) {
        if (repo.findBySku(product.getSku()) != null)
            throw new IllegalArgumentException("SKU already exists");
        if (product.getPrice().signum() <= 0)
            throw new IllegalArgumentException("Invalid price");
        return repo.save(product);
    }
}