package com.example.demo.cardealer.service;

import com.example.demo.cardealer.entities.cars.Car;
import com.example.demo.cardealer.entities.cars.CarWithPartsDTO;
import com.example.demo.cardealer.repositories.CarRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper mapper;
    private final Gson gson;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
        mapper = new ModelMapper();
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    @Transactional
    public String getAllCarsWithTheirListOfParts() {
        List<Car> cars =  this.carRepository.getAllCarsWithTheirListOfParts();
        List<CarWithPartsDTO> carsWithParts = cars.stream().map(car -> mapper.map(car, CarWithPartsDTO.class)).toList();

        return gson.toJson(carsWithParts);
    }
}
