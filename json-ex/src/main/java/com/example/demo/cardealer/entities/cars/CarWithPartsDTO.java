package com.example.demo.cardealer.entities.cars;

import com.example.demo.cardealer.entities.parts.PartDTO;

import java.util.List;

public class CarWithPartsDTO {
   private CarDTO car;
    private List<PartDTO> parts;

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    public List<PartDTO> getParts() {
        return parts;
    }

    public void setParts(List<PartDTO> parts) {
        this.parts = parts;
    }
}
