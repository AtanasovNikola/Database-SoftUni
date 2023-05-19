package com.example.demo.productshop.services;

import com.example.demo.productshop.entities.category.CategoryStats;
import com.example.demo.productshop.entities.product.ProductInRangeDTO;

import java.util.List;

public interface ProductService {
    List<ProductInRangeDTO> productsInRange(float from, float to);

    List<CategoryStats> getCategoryStatistics();
}
