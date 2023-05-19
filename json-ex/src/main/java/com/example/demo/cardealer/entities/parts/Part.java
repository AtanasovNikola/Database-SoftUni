package com.example.demo.cardealer.entities.parts;

import com.example.demo.cardealer.entities.suppliers.Supplier;
import com.example.demo.cardealer.entities.cars.Car;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parts")
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private BigDecimal price;
    private int quantity;

    @ManyToMany(targetEntity = Car.class,mappedBy = "parts")
    private List<Car> cars;

    @ManyToOne(targetEntity = Supplier.class)
    private Supplier supplier;

    public Part(){
        this.cars = new ArrayList<>();
    }

    public Part(String name, BigDecimal price, int quantity) {
        this();
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
