package com.example.demo.repositories;

import com.example.demo.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,Long> {

    Set<Ingredient> findByNameStartingWith(char letter);

    Set<Ingredient> findByNameInOrderByPriceAsc(List<String> name);

    @Query("UPDATE Ingredient i " +
            "SET i.price = i.price * :amount")
    @Modifying
    void increasePriceBy(@Param("amount") BigDecimal amount);
}
