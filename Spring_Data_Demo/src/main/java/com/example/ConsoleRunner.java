package com.example;


import com.example.models.User;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {
    @Autowired(required = true)
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        User first = new User("qna", 22);
        userService.registerUser(first);

        User second = new User("qna", 22);
        userService.registerUser(second);
    }
}