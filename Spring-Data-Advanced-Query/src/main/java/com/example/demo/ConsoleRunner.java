package com.example.demo;

import com.example.demo.entities.Ingredient;
import com.example.demo.entities.Size;
import com.example.demo.services.IngredientService;
import com.example.demo.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    @Autowired
    public ConsoleRunner(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {
        shampooService.selectShampoosByGivenSizeOrderedById(Size.MEDIUM);
        shampooService.selectShampoosBySizeOrLabel(Size.MEDIUM, 10L);
        shampooService.selectShampoosByPrice(BigDecimal.valueOf(5));
        ingredientService.selectIngredientsByStartingLetter('M');
        ingredientService.selectIngredientsByName(List.of("Lavender", "Herbs", "Apple"));
        shampooService.countShampoosByPrice(BigDecimal.valueOf(8.50));
        shampooService.selectShampoosByIngredients(Set.of("Berry", "Mineral-Collagen"));
        shampooService.selectShampoosByIngredientsCount(2);
        ingredientService.increasePriceBy(1.1);
    }
}
