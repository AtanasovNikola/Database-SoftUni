package com.example.demo.repositories;

import com.example.demo.entities.Shampoo;
import com.example.demo.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Set;


@Repository
public interface ShampooRepository extends JpaRepository<Shampoo,Integer> {
Set<Shampoo> findBySizeOrderById(Size size);

    Set<Shampoo> findBySizeOrLabelIdOrderByPriceAsc(Size medium, long label);

    Set<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    Integer countByPriceLessThan(BigDecimal price);

    @Query("SELECT DISTINCT s.brand FROM Shampoo s " +
            "JOIN s.ingredients i " +
            "WHERE i.name IN(:ingredientNames)")
    Set<String> findByIngredientsName(@Param("ingredientNames") Set<String> ingredients);

@Query("SELECT s.brand FROM Shampoo s " +
        "WHERE s.ingredients.size < :ingredientsCount")
    Set<String> findByIngredientsCount(@Param("ingredientsCount") int count);
}
