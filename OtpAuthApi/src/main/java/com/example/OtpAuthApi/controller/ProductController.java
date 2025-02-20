package com.example.OtpAuthApi.controller;

import com.example.OtpAuthApi.entity.Category;
import com.example.OtpAuthApi.entity.Product;
import com.example.OtpAuthApi.service.CategoryService;
import com.example.OtpAuthApi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable Long categoryId) {
        return productService.getProductsByCategory(categoryId);
    }

    @GetMapping("/top10")
    public List<Product> getTop10ProductsByQuantitySold() {
        return productService.getTop10ProductsByQuantitySold();
    }

    @GetMapping("/recent")
    public List<Product> getProductsCreatedWithinLast7Days() {
        return productService.getProductsCreatedWithinLast7Days();
    }

    @GetMapping("/category")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
}