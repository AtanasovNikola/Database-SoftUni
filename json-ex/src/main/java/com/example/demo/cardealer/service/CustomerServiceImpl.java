package com.example.demo.cardealer.service;

import com.example.demo.cardealer.entities.customers.Customer;
import com.example.demo.cardealer.repositories.CustomerRepository;
import com.google.gson.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private Gson gson;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("YYYY-MM-DD")
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
    }

    static class LocalDateAdapter implements JsonSerializer<LocalDate> {

        public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // "yyyy-mm-dd"
        }
    }

    @Transactional
    @Override
    public String getAllCustomersOrderedByBirthDate() {
        List<Customer> customers = this.customerRepository.findAll();
        customers.sort(Comparator.comparing(Customer::getBirthDate).thenComparing(Customer::isYoungDriver).reversed());

        String json = this.gson.toJson(customers);
        return json;
    }
}
