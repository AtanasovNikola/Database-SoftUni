package com.example.demo.cardealer.entities.cars;

import com.example.demo.cardealer.entities.parts.Part;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String make;
    private String model;
    @Column(name = "travelled_distance")
    private long travelledDistance;

    @ManyToMany
    private List<Part> parts;

    public Car(){
        this.parts = new ArrayList<>();
    }

    public Car(String make, String model, long travelledDistance) {
        this();
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
