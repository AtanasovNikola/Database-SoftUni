package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void registerUser(User user) {
        Optional<User> result = this.userRepository.findByUsername(user.getUsername());
        if (result.isEmpty()){
            this.userRepository.save(user);
        }
    }

    @Override
    public void getUsersByEmailProvider(String provider) {
        Optional<User> result = userRepository.findByEmailContaining(provider);

        if (result.isEmpty()){
            System.out.println("No users found with email domain provider");
        }else{
            result.ifPresent(user -> System.out.println(user.getEmail()));
        }
    }
}
