package com.example.demo.productshop.entities.user;

import com.example.demo.productshop.entities.product.Product;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    private int age;

    @OneToMany(mappedBy = "seller", targetEntity = Product.class)
    private List<Product> soldItems;
    @OneToMany(mappedBy = "buyer", targetEntity = Product.class)
    private List<Product> boughtItems;
    @ManyToMany
    private Set<User> friends;

    public User() {
        this.soldItems = new ArrayList<>();
        this.boughtItems = new ArrayList<>();
        this.friends = new HashSet<>();
    }

    public User(String firstName, String lastName, int age) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Product> getSoldItems() {
        return soldItems;
    }

    public void setSoldItems(List<Product> soldItems) {
        this.soldItems = soldItems;
    }

    public List<Product> getBoughtItems() {
        return boughtItems;
    }

    public void setBoughtItems(List<Product> boughtItems) {
        this.boughtItems = boughtItems;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }
}
