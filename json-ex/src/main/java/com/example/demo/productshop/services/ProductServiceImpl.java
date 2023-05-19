package com.example.demo.productshop.services;

import com.example.demo.productshop.entities.category.CategoryStats;
import com.example.demo.productshop.entities.product.ProductInRangeDTO;
import com.example.demo.productshop.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public List<ProductInRangeDTO> productsInRange(float from, float to) {
        return this.productRepository
                .findAllByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal.valueOf(from), BigDecimal.valueOf(to));
    }

    @Override
    public List<CategoryStats> getCategoryStatistics() {
return productRepository.getCategoryStats();

    }
}
