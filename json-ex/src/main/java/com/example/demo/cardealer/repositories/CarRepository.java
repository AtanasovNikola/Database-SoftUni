package com.example.demo.cardealer.repositories;

import com.example.demo.cardealer.entities.cars.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {
    @Query("SELECT c FROM Car c" +
            " JOIN c.parts")
    List<Car> getAllCarsWithTheirListOfParts();
}
