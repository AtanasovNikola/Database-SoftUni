package com.example.demo.productshop.services;

import com.example.demo.productshop.entities.user.User;
import com.example.demo.productshop.entities.user.UserWithSoldProductDTO;
import com.example.demo.productshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

        this.mapper = new ModelMapper();
    }

    @Override
    public List<UserWithSoldProductDTO> getAllUsersWhoHaveAtLeastOneItemSold() {
        List<User> allWithSoldProducts = this.userRepository.findAllWithSoldProducts();
      return allWithSoldProducts
                .stream()
                .map(user -> this.mapper.map(user, UserWithSoldProductDTO.class))
                .toList();

    }
}
