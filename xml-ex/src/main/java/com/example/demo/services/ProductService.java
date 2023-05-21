package com.example.demo.services;

import com.example.demo.entities.products.ProductsInRangeDTO;


public interface ProductService {
    ProductsInRangeDTO getAllProductsInRange(float from,float to);
}
