package com.example.demo.services;

import com.example.demo.entities.products.Product;
import com.example.demo.entities.products.ProductInRangeDTO;
import com.example.demo.entities.products.ProductsInRangeDTO;
import com.example.demo.entities.users.User;
import com.example.demo.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    private final TypeMap<Product, ProductInRangeDTO> productInRangeDTOTypeMap;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        mapper = new ModelMapper();
        Converter<User, String> userToFullName =
                context -> context.getSource() == null ? null : context.getSource().getFullName();
        TypeMap<Product, ProductInRangeDTO> typeMap = this.mapper.createTypeMap(Product.class, ProductInRangeDTO.class);
        this.productInRangeDTOTypeMap = typeMap.addMappings(
                map -> map.using(userToFullName)
                        .map(Product::getSeller, ProductInRangeDTO::setSeller));
    }

    @Override
    @Transactional
    public ProductsInRangeDTO getAllProductsInRange(float from, float to) {
        List<Product> products = this.productRepository.findByPriceBetweenWhereBuyerIsNull(BigDecimal.valueOf(from), BigDecimal.valueOf(to));
        List<ProductInRangeDTO> productInRangeDTOS = products.stream()
                .map(p -> mapper.map(p, ProductInRangeDTO.class)).toList();
        return new ProductsInRangeDTO(productInRangeDTOS);
    }
}
