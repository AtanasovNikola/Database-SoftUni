package com.example.demo.productshop.repositories;

import com.example.demo.productshop.entities.category.CategoryStats;
import com.example.demo.productshop.entities.product.Product;
import com.example.demo.productshop.entities.product.ProductInRangeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT new com.example.demo.productshop.entities.product.ProductInRangeDTO(" +
            "p.name,p.price,p.seller.firstName,p.seller.lastName)" +
            " FROM Product p" +
            " WHERE p.price > :from AND p.price < :to AND p.buyer IS NULL" +
            " ORDER BY p.price ASC ")
    List<ProductInRangeDTO> findAllByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal from, BigDecimal to);

    @Query("SELECT new com.example.demo.productshop.entities.category.CategoryStats(" +
            " c.name , count(p) ," +
            " avg(p.price) , sum(p.price))" +
            " FROM Product p" +
            " JOIN p.categories c" +
            " GROUP BY c")
    List<CategoryStats> getCategoryStats();
}
