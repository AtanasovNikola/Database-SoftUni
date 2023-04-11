package com.example.services;

import com.example.models.User;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerUser(User user) {
        Optional<User> found = this.userRepository
                .findByUsername(user.getUsername());

        if (found.isEmpty()) {
            this.userRepository.save(user);
        }
    }
}
