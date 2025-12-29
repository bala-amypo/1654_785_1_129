package com.example.demo.service.impl;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import com.example.demo.service.ProductService;
import java.math.BigDecimal;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product p) {
        if (p.getPrice().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Price must be positive");

        productRepository.findBySku(p.getSku()).ifPresent(x -> {
            throw new IllegalArgumentException("SKU already exists");
        });

        return productRepository.save(p);
    }

    public Product updateProduct(Long id, Product p) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        existing.setName(p.getName());
        existing.setPrice(p.getPrice());
        return productRepository.save(existing);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    public void deactivateProduct(Long id) {
        Product p = getProductById(id);
        p.setActive(false);
        productRepository.save(p);
    }
}
