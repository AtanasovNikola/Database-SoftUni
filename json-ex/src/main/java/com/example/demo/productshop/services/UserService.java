package com.example.demo.productshop.services;

import com.example.demo.productshop.entities.user.UserWithSoldProductDTO;

import java.util.List;

public interface UserService {
    List<UserWithSoldProductDTO> getAllUsersWhoHaveAtLeastOneItemSold();
}
