package com.example.demo.services;

import com.example.demo.entities.Town;
import com.example.demo.repositories.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TownServiceImpl implements TownService {
    @Autowired
    TownRepository townRepository;
    @Override
    public void addTown(Town town) {
        Optional<Town> result = townRepository.findByName(town.getName());

        if (result.isEmpty()){
            townRepository.save(town);
        }
    }
}
