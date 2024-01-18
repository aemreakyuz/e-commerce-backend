package com.ecommerce.backend.repository;

import com.ecommerce.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query("SELECT p FROM Product p JOIN p.category c WHERE c.title = :categoryName")
    List<Product> productsByCategory(String categoryName);

    @Query("SELECT p from Product p WHERE p.name = :productName")
    Optional<Product> findByProductName(String productName);

}
