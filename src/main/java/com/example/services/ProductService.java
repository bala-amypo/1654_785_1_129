package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public Product createProduct(Product p) {
        return repo.save(p);
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }
}
