package com.example.demo.repositories;

import com.example.demo.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository  extends JpaRepository<Author,Integer> {
    Optional<Author> findById(int id);
    List<Author> findDistinctByBooksReleaseDateBefore(LocalDate releaseDate);
    List<Author> findAll();
    Author findByFirstNameAndLastName(String firstName, String lastName);
}
