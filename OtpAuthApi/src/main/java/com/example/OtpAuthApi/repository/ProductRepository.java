package com.example.OtpAuthApi.repository;

import com.example.OtpAuthApi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);

    @Query("SELECT p FROM Product p ORDER BY p.quantitySold DESC")
    List<Product> findTop10ByQuantitySold();

    List<Product> findByCreatedAtAfter(LocalDateTime dateTime);
}