package com.example.demo.cardealer;

import com.example.demo.cardealer.service.CarService;
import com.example.demo.cardealer.service.CustomerService;
import com.example.demo.cardealer.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CarDealerRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final CustomerService customerService;
    private final CarService carService;


    @Autowired
    public CarDealerRunner(SeedService seedService, CustomerService customerService, CarService carService) {
        this.seedService = seedService;
        this.customerService = customerService;
        this.carService = carService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedService.seedAll();
        System.out.println(customerService.getAllCustomersOrderedByBirthDate());
        System.out.println(carService.getAllCarsWithTheirListOfParts());
    }
}
