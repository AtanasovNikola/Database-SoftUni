package com.example.demo.services;


import com.example.demo.entities.Author;


public interface AuthorService {
    Author getRandomAuthor();
    void findBookWithAuthorNameEndingWith(String endingString);
}
