package com.example.demo.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.demo.repository.ProductRepository;
import com.example.demo.model.Product;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product updateProduct(Long id, Product p) {
        Product existing = productRepository.findById(id).orElseThrow();
        existing.setName(p.getName());
        existing.setCategory(p.getCategory());
        existing.setPrice(p.getPrice());
        existing.setActive(true);
        return productRepository.save(existing);
    }
}
