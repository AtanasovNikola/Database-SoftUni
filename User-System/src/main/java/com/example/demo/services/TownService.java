package com.example.demo.services;

import com.example.demo.entities.Town;
import org.springframework.stereotype.Service;

@Service
public interface TownService {
    void addTown(Town town);
}
