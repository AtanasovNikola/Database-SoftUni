package com.example.demo.services;

import com.example.demo.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void selectIngredientsByStartingLetter(char letter) {
        ingredientRepository.findByNameStartingWith(letter).forEach(System.out::println);
    }

    @Override
    public void selectIngredientsByName(List<String> name) {
        ingredientRepository.findByNameInOrderByPriceAsc(name).forEach(System.out::println);
    }

    @Override
    @Transactional
    public void increasePriceBy(double amount) {
        BigDecimal bigDecimal = BigDecimal.valueOf(amount);
        ingredientRepository.increasePriceBy(bigDecimal);
    }
}
