package com.example.demo.cardealer.entities.sales;

import com.example.demo.cardealer.entities.cars.Car;
import com.example.demo.cardealer.entities.customers.Customer;
import jakarta.persistence.*;

@Entity
@Table(name = "sales")
public class Sale {
    private static final double ADDITIONAL_DISCOUNT_FOR_YOUNG_DRIVER = 0.05;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "discount_percentage")
    private double discountPercentage;
    @OneToOne
    private Car car;
    @ManyToOne
    private Customer customer;

    public Sale() {
    }

    public Sale(double discountPercentage, Car car, Customer customer) {
        this.car = car;
        this.customer = customer;
        if (customer.isYoungDriver()) {
            this.discountPercentage = discountPercentage + ADDITIONAL_DISCOUNT_FOR_YOUNG_DRIVER;

        } else {
            this.discountPercentage = discountPercentage;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
