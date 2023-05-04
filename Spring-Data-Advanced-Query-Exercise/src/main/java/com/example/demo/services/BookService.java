package com.example.demo.services;

import com.example.demo.entities.AgeRestriction;
import com.example.demo.entities.EditionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface BookService {
    void findByAgeRestriction(AgeRestriction ageRestriction);

    void findTheGoldenEditionBooksWhichHaveLessThan5000Copies(EditionType gold, int copies);

    void findBooksByPrice(BigDecimal lowerThan, BigDecimal higherThan);

    void findBooksNotReleasedInAGivenYear(LocalDate releaseDate);

    void findBookBeforeReleasedDate(LocalDate localDate);

    void findBookTitleWhichContains(String containsString);

    void findBookTitleWrittenByAuthorLastNameStartingWith(String startsWith);

    void countOfBooksWhichTitleIsLongerThan(int symbols);

    void countOfBookCopiesByAuthor();

    void findTheGivenBook(String title);

    void increaseBookCopiesOfAllBooksReleasedAfter(LocalDate releaseDate, int copies);

    void removeBooksWhichCopiesAreLowerThan(int copies);

}
