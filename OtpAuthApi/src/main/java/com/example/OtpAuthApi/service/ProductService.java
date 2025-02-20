package com.example.OtpAuthApi.service;

import com.example.OtpAuthApi.entity.Product;
import com.example.OtpAuthApi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public List<Product> getTop10ProductsByQuantitySold() {
        return productRepository.findTop10ByQuantitySold();
    }

    public List<Product> getProductsCreatedWithinLast7Days() {
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);
        return productRepository.findByCreatedAtAfter(sevenDaysAgo);
    }
}