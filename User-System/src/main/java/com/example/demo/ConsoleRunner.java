package com.example.demo;


import com.example.demo.entities.Town;
import com.example.demo.entities.User;
import com.example.demo.services.TownService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ConsoleRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;
    @Autowired
    private TownService townService;


    @Override
    public void run(String... args) throws Exception {
        Town town = new Town("Plovdiv", "Bulgaria");
        Town town2 = new Town("Varna", "Bulgaria");

        User user = new User("nikola99", "aA1sd!", "stephan@softuni.bg",
                LocalDateTime.now(), LocalDateTime.now(), 24,
                false, "Nikola", "Atanasov", town, town2);

        User user2 = new User("niko99", "sT1kp!", "nikola@softuni.bg",
                LocalDateTime.now(), LocalDateTime.now(), 24,
                false, "Niko", "Atanasovv", town, town2);

        townService.addTown(town);
        townService.addTown(town2);

        userService.registerUser(user);
        userService.registerUser(user2);

        String provider = user.getEmail().substring(getIndex(user.getEmail()));
        userService.getUsersByEmailProvider(provider);



    }

    private int getIndex(String email) {
        int length = email.length();
        for (int i = 0; i < length; i++) {
            char currentSymbol = email.charAt(i);

            if (currentSymbol == '@') {
                return i;
            }
        }
        return 0;
    }
}
