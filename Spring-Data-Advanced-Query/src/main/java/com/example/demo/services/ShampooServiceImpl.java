package com.example.demo.services;

import com.example.demo.entities.Ingredient;
import com.example.demo.entities.Size;
import com.example.demo.repositories.IngredientRepository;
import com.example.demo.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class ShampooServiceImpl implements ShampooService {

    private final ShampooRepository shampooRepository;


    @Autowired
    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;

    }

    @Override
    public void selectShampoosByGivenSizeOrderedById(Size size) {
        shampooRepository.findBySizeOrderById(size).forEach(System.out::println);
    }

    @Override
    public void selectShampoosBySizeOrLabel(Size medium, long label) {
        shampooRepository.findBySizeOrLabelIdOrderByPriceAsc(medium, label).forEach(System.out::println);
    }

    @Override
    public void selectShampoosByPrice(BigDecimal price) {
        shampooRepository.findByPriceGreaterThanOrderByPriceDesc(price).forEach(System.out::println);
    }

    @Override
    public void countShampoosByPrice(BigDecimal price) {
        System.out.println(shampooRepository.countByPriceLessThan(price));
    }

    @Override
    public void selectShampoosByIngredients(Set<String> ingredients) {
        shampooRepository.findByIngredientsName(ingredients).forEach(System.out::println);
    }

    @Override
    public void selectShampoosByIngredientsCount(int count) {
        shampooRepository.findByIngredientsCount(count).forEach(System.out::println);
    }


}
