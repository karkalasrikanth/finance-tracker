package com.replenishmentsystem.inventoryservice.service;

import com.replenishmentsystem.inventoryservice.entity.Product;
import com.replenishmentsystem.inventoryservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
