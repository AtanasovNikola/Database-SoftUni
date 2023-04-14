package com.example.demo.entities;


import com.example.demo.annotations.Password;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Validated
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30, nullable = false)
    private String username;

    @Column(length = 50, nullable = false)
    @Password(minLength = 4,
            maxLength = 30,
            containsDigit = true,
            containsLowerCase = true,
            containsSpecialSymbol = true,
            containsUpperCase = true,
            message = "Invalid password")
    private String password;

    @Column(nullable = false)
    @Email(regexp = "[a-zA-Z0-9]+([._-][a-zA-Z0-9]+)*@[a-zA-Z]+(-[a-zA-Z]+)*([.][a-zA-Z]+(-[a-zA-Z]+)*)+"
    ,message = "Invalid email")
    private String email;

    @Column(name = "register_on")
    private LocalDateTime registerOn;

    @Column(name = "last_time_logged_in")
    private LocalDateTime lastTimeLoggedIn;

    private int age;

    @Column(name = "isDeleted")
    private boolean isDeleted;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String fullName;

    @ManyToMany
    @JoinTable(
            name = "friendship",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<User> friends;

    @ManyToOne
    @JoinColumn(name = "born_town_id")
    private Town bornTown;

    @ManyToOne
    @JoinColumn(name = "current_living_town_id")
    private Town currentLivingTown;

    public User() {
        this.friends = new HashSet<>();
    }

    public User(String username, String password,  String email, LocalDateTime registerOn, LocalDateTime lastTimeLoggedIn,
                int age, boolean isDeleted, String firstName, String lastName,
                 Town bornTown, Town currentlyLivingTown) {
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.setRegisterOn(registerOn);
        this.setLastTimeLoggedIn(lastTimeLoggedIn);
        this.setAge(age);
        this.setDeleted(isDeleted);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setBornTown(bornTown);
        this.setCurrentlyLivingTown(currentlyLivingTown);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws IllegalArgumentException {
        this.email = email;
    }

    public LocalDateTime getRegisterOn() {
        return registerOn;
    }

    public void setRegisterOn(LocalDateTime registerOn) {
        this.registerOn = registerOn;
    }

    public LocalDateTime getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(LocalDateTime lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
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

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }


    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Town getBornTown() {
        return bornTown;
    }

    public void setBornTown(Town bornTown) {
        this.bornTown = bornTown;
    }

    public Town getCurrentlyLivingTown() {
        return currentLivingTown;
    }

    public void setCurrentlyLivingTown(Town currentLivingTown) {
        this.currentLivingTown = currentLivingTown;
    }
}
