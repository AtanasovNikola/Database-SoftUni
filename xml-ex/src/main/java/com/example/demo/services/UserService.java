package com.example.demo.services;

import com.example.demo.entities.users.UsersExportDTO;

public interface UserService {
     UsersExportDTO getAllUsersWhoHaveAtLeastOneItemSold();
}
