package com.example.demo.cardealer.repositories;

import com.example.demo.cardealer.entities.parts.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part,Integer> {
}
