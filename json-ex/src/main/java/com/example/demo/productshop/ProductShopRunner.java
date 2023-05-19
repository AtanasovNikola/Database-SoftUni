package com.example.demo.productshop;

import com.example.demo.productshop.entities.category.CategoryStats;
import com.example.demo.productshop.entities.product.ProductInRangeDTO;
import com.example.demo.productshop.entities.user.UserWithSoldProductDTO;
import com.example.demo.productshop.services.ProductService;
import com.example.demo.productshop.services.SeedService;
import com.example.demo.productshop.services.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductShopRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final ProductService productService;
    private final UserService userService;
    private Gson gson;

    @Autowired
    public ProductShopRunner(SeedService seedService, ProductService productService, Gson gson, UserService userService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        this.seedService.seedUsers();
        this.seedService.seedCategories();
        this.seedService.seedProducts();
   getAllProductsInRange(500,1000);
    getAllUsersWhoHaveAtLeastOneItemSoldAndOneBuyer();
        getAllCategories();

    }



    private void getAllCategories() {
        List<CategoryStats> categories = this.productService.getCategoryStatistics();
        String json = this.gson.toJson(categories);
        System.out.println(json);
    }

    private void getAllProductsInRange(float from, float to) {
        List<ProductInRangeDTO> productInRangeDTOS = this.productService.productsInRange(from, to);
        String json = this.gson.toJson(productInRangeDTOS);
        System.out.println(json);
    }

    private void getAllUsersWhoHaveAtLeastOneItemSoldAndOneBuyer() {
        List<UserWithSoldProductDTO> users = this.userService.getAllUsersWhoHaveAtLeastOneItemSold();
        String json = gson.toJson(users);
        System.out.println(json);
    }
}
