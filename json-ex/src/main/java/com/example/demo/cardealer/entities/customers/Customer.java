package com.example.demo.cardealer.entities.customers;

import com.example.demo.cardealer.entities.sales.Sale;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private int id;
    @Expose
    private String name;
    @Expose
    private LocalDate birthDate;
    @Column(name = "is_young_driver")
    @Expose
    private boolean isYoungDriver;

    @OneToMany(targetEntity = Sale.class, mappedBy = "customer")
    @Expose
    private final Set<Sale> sales;

    public Customer() {
        this.sales = new HashSet<>();
    }

    public Customer(String name, LocalDate birthDate) {
        this();
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = this.birthDate.getYear() - 18 >= 2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        this.isYoungDriver = youngDriver;
    }

    public Set<Sale> getSales() {
        return sales;
    }
}
