package com.example.demo.repositories;

import com.example.demo.entities.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("SELECT p FROM Product p" +
            " WHERE p.buyer IS NULL AND p.price BETWEEN :from and :to" +
            " ORDER BY p.price")
    List<Product> findByPriceBetweenWhereBuyerIsNull(BigDecimal from , BigDecimal to);
}
