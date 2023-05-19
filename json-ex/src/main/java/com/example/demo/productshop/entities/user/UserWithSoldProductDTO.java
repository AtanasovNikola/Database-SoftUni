package com.example.demo.productshop.entities.user;

import com.example.demo.productshop.entities.product.SoldProductDTO;

import java.util.List;

public class UserWithSoldProductDTO {
    private String firstName;
    private String lastName;
    private List<SoldProductDTO> soldItems;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<SoldProductDTO> getSoldItems() {
        return soldItems;
    }

    public void setSoldItems(List<SoldProductDTO> soldItems) {
        this.soldItems = soldItems;
    }
}
