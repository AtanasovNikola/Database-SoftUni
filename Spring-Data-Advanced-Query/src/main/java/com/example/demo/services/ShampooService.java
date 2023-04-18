package com.example.demo.services;

import com.example.demo.entities.Size;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ShampooService {
    void selectShampoosByGivenSizeOrderedById(Size size);

    void selectShampoosBySizeOrLabel(Size medium, long label);

    void selectShampoosByPrice(BigDecimal price);


    void countShampoosByPrice(BigDecimal price);

    void selectShampoosByIngredients(Set<String> ingredients);

    void selectShampoosByIngredientsCount(int count);
}
