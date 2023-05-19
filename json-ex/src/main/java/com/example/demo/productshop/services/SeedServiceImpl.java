package com.example.demo.productshop.services;

import com.example.demo.productshop.entities.category.Category;
import com.example.demo.productshop.entities.category.CategoryImportDTO;
import com.example.demo.productshop.entities.product.Product;
import com.example.demo.productshop.entities.product.ProductImportDTO;
import com.example.demo.productshop.entities.user.User;
import com.example.demo.productshop.entities.user.UserImportDTO;
import com.example.demo.productshop.repositories.CategoryRepository;
import com.example.demo.productshop.repositories.ProductRepository;
import com.example.demo.productshop.repositories.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

@Service
public class SeedServiceImpl implements SeedService {
    private static final String USERS_JSON_PATH = "src/main/resources/users.json";
    private static final String CATEGORIES_JSON_PATH = "src/main/resources/categories.json";
    private static final String PRODUCTS_JSON_PATH = "src/main/resources/products.json";
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;

        this.modelMapper = new ModelMapper();
        this.gson = new GsonBuilder()
                .setPrettyPrinting().create();
    }

    @Override
    public void seedUsers() throws FileNotFoundException {
        FileReader fileReader = new FileReader(USERS_JSON_PATH);
        UserImportDTO[] userDTO = gson.fromJson(fileReader, UserImportDTO[].class);

        List<User> users = Arrays.stream(userDTO)
                .map(userImportDTO -> this.modelMapper.map(userImportDTO, User.class))
                .toList();

        this.userRepository.saveAll(users);
    }

    @Override
    public void seedProducts() throws FileNotFoundException {
        FileReader fileReader = new FileReader(PRODUCTS_JSON_PATH);
        ProductImportDTO[] productImportDTOS = gson.fromJson(fileReader, ProductImportDTO[].class);

        List<Product> products = Arrays.stream(productImportDTOS)
                .map(productDTO -> this.modelMapper.map(productDTO, Product.class))
                .map(this::setBuyer)
                .map(this::setSeller)
                .map(this::setCategory)
                .toList();

        this.productRepository.saveAll(products);
    }

    private Product setCategory(Product product) {
        Random random = new Random();
        int categoriesCount = random.nextInt((int) categoryRepository.count()) + 1;
        Set<Category> categorySet = new HashSet<>();
        for (int i = 0; i < categoriesCount; i++) {
            Optional<Category> category = categoryRepository
                    .findById(random.nextInt((int) categoryRepository.count()) + 1);
            categorySet.add(category.get());
        }
        product.setCategories(categorySet);
        return product;
    }

    private Product setSeller(Product product) {
        Optional<User> user = getRandomUser();
        product.setSeller(user.get());
        return product;
    }

    private Product setBuyer(Product product) {
        if (product.getPrice().compareTo(BigDecimal.valueOf(700)) > 0) {
            return product;
        }
        Optional<User> user = getRandomUser();
        product.setBuyer(user.get());
        return product;

    }

    private Optional<User> getRandomUser() {
        Random random = new Random();
        int userId = random.nextInt((int) (userRepository.count())) + 1;
        return userRepository.findById(userId);
    }

    @Override
    public void seedCategories() throws FileNotFoundException {
        FileReader fileReader = new FileReader(CATEGORIES_JSON_PATH);
        CategoryImportDTO[] categoryImportDTOS = gson.fromJson(fileReader, CategoryImportDTO[].class);

        List<Category> users = Arrays.stream(categoryImportDTOS)
                .map(categoryDTO -> this.modelMapper.map(categoryDTO, Category.class))
                .toList();

        this.categoryRepository.saveAll(users);
    }
}
