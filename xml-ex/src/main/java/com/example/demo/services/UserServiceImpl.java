package com.example.demo.services;

import com.example.demo.entities.users.User;
import com.example.demo.entities.users.UserExportDTO;
import com.example.demo.entities.users.UsersExportDTO;
import com.example.demo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        mapper = new ModelMapper();
    }

    @Override
    @Transactional
    public UsersExportDTO getAllUsersWhoHaveAtLeastOneItemSold() {
        List<User> users = this.userRepository.findAllUsersWhoHaveAtLeastOneItemSold();
        List<UserExportDTO> usersDTO = users.stream()
                .map(u -> mapper.map(u, UserExportDTO.class)).toList();
        return new UsersExportDTO(usersDTO);
    }
}
