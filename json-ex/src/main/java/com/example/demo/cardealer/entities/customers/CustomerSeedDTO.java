package com.example.demo.cardealer.entities.customers;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class CustomerSeedDTO {
    @SerializedName("name")
    private String name;

    @SerializedName("birthDate")
    private LocalDate birthDate;

    @SerializedName("isYoungDriver")
    private boolean isYoungDriver;


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
        isYoungDriver = youngDriver;
    }
}
