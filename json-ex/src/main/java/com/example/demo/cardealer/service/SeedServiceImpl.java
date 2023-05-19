package com.example.demo.cardealer.service;

import com.example.demo.cardealer.entities.*;
import com.example.demo.cardealer.entities.cars.Car;
import com.example.demo.cardealer.entities.customers.Customer;
import com.example.demo.cardealer.entities.customers.CustomerSeedDTO;
import com.example.demo.cardealer.entities.parts.Part;
import com.example.demo.cardealer.entities.parts.PartSeedDTO;
import com.example.demo.cardealer.entities.sales.Sale;
import com.example.demo.cardealer.entities.suppliers.Supplier;
import com.example.demo.cardealer.entities.suppliers.SupplierSeedDTO;
import com.example.demo.cardealer.repositories.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.*;

@Service
public class SeedServiceImpl implements SeedService {
    private static final String SUPPLIERS_JSON_PATH = "src/main/resources/suppliers.json";
    private static final String PARTS_JSON_PATH = "src/main/resources/parts.json";
    private static final String CARS_JSON_PATH = "src/main/resources/cars.json";
    private static final String CUSTOMER_JSON_PATH = "src/main/resources/customers.json";
    private final Gson gson;
    private final ModelMapper mapper;
    private final SupplierRepository supplierRepository;
    private final PartRepository partRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;

    @Autowired
    public SeedServiceImpl(SupplierRepository supplierRepository, PartRepository partRepository, CarRepository carRepository, CustomerRepository customerRepository, SaleRepository saleRepository) {
        this.supplierRepository = supplierRepository;
        this.partRepository = partRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;

        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.mapper = new ModelMapper();
    }

    @Override
    public void seedSuppliers() throws FileNotFoundException {
        FileReader fileReader = new FileReader(SUPPLIERS_JSON_PATH);
        SupplierSeedDTO[] supplierDTO = gson.fromJson(fileReader, SupplierSeedDTO[].class);

        List<Supplier> suppliers = Arrays.stream(supplierDTO)
                .map(supplier -> this.mapper.map(supplier, Supplier.class))
                .toList();

        this.supplierRepository.saveAll(suppliers);
    }

    @Override
    public void seedParts() throws FileNotFoundException {
        FileReader fileReader = new FileReader(PARTS_JSON_PATH);
        PartSeedDTO[] partDTO = gson.fromJson(fileReader, PartSeedDTO[].class);

        List<Part> parts = Arrays.stream(partDTO)
                .map(part -> this.mapper.map(part, Part.class))
                .map(this::setRandomSupplier)
                .toList();

        this.partRepository.saveAll(parts);
    }

    private Part setRandomSupplier(Part part) {
        int supplierId = getRandomNumber(supplierRepository);
        Optional<Supplier> supplier = this.supplierRepository.findById(supplierId);

        part.setSupplier(supplier.get());
        return part;
    }

    private int getRandomNumber(JpaRepository jpaRepository) {
        Random random = new Random();

        return random.nextInt((int) jpaRepository.count()) + 1;
    }

    @Override
    public void seedCars() throws FileNotFoundException {
        FileReader fileReader = new FileReader(CARS_JSON_PATH);
        Car[] carArr = gson.fromJson(fileReader, Car[].class);

        List<Car> cars = Arrays.stream(carArr)
                .map(this::setRandomParts)
                .toList();

        this.carRepository.saveAll(cars);
    }

    private Car setRandomParts(Car car) {
        Random random = new Random();
        int partsCount = random.nextInt(3, 6);
        List<Part> parts = new ArrayList<>();
        for (int i = 0; i < partsCount; i++) {
            int partId = getRandomNumber(partRepository);
            Optional<Part> part = this.partRepository.findById(partId);
            parts.add(part.get());
        }
        car.setParts(parts);
        return car;
    }

    @Override
    public void seedCustomers() throws FileNotFoundException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .create();
        FileReader fileReader = new FileReader(CUSTOMER_JSON_PATH);
        TypeToken<List<CustomerSeedDTO>> typeToken = new TypeToken<>() {
        };
        List<CustomerSeedDTO> customerDTO = gson.fromJson(fileReader, typeToken.getType());
        List<Customer> customers = customerDTO.stream().map(customer -> mapper.map(customer, Customer.class))
                .toList();
        this.customerRepository.saveAll(customers);
    }

    @Override
    public void seedSales() {
        Random random = new Random();
        List<Sale> sales = new ArrayList<>();
        for (int saleCount = 0; saleCount < 20; saleCount++) {
            int carId = getRandomNumber(carRepository);
            int customerId = getRandomNumber(customerRepository);
            Car randomCar = carRepository.findById(carId).get();
            Customer randomCustomer = customerRepository.findById(customerId).get();
            int index = random.nextInt(7);
            double randomDiscount = getRandomDiscount(index);
            Sale sale = new Sale(randomDiscount, randomCar, randomCustomer);
            sales.add(sale);
        }
        this.saleRepository.saveAll(sales);
    }

    private double getRandomDiscount(int index) {
        double discount;
        switch (index) {
            case 1 -> discount = 0.05;
            case 2 -> discount = 0.1;
            case 3 -> discount = 0.15;
            case 4 -> discount = 0.2;
            case 5 -> discount = 0.3;
            case 6 -> discount = 0.4;
            case 7 -> discount = 0.5;
            default -> discount = 0;
        }
        return discount;
    }
}
