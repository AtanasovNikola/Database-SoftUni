package com.example.demo.services;

import java.util.List;

public interface IngredientService {
    void selectIngredientsByStartingLetter(char letter);

    void selectIngredientsByName(List<String> name);

    void increasePriceBy(double amount);
}
