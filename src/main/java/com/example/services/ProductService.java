package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

import java.util.List;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        if (productRepository.findBySku(product.getSku()).isPresent()) {
            throw new IllegalArgumentException("SKU already exists");
        }
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Product existing = getProductById(id);
        existing.setName(product.getName());
        existing.setCategory(product.getCategory());
        existing.setPrice(product.getPrice());
        return productRepository.save(existing);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deactivateProduct(Long id) {
        Product p = getProductById(id);
        p.setActive(false);
        productRepository.save(p);
    }
}
