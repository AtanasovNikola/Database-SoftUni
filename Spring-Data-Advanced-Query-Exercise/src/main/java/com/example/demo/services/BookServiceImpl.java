package com.example.demo.services;

import com.example.demo.entities.AgeRestriction;
import com.example.demo.entities.EditionType;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void findByAgeRestriction(AgeRestriction ageRestriction) {
        bookRepository.findByAgeRestriction(ageRestriction)
                .forEach(System.out::println);
    }

    @Override
    public void findTheGoldenEditionBooksWhichHaveLessThan5000Copies(EditionType gold, int copies) {
        bookRepository.findByEditionTypeAndCopiesLessThan(gold, copies).forEach(System.out::println);
    }

    @Override
    public void findBooksByPrice(BigDecimal lowerThan, BigDecimal higherThan) {
        bookRepository.findByPriceLessThanGreaterThan(lowerThan, higherThan).forEach(System.out::println);
    }

    @Override
    public void findBooksNotReleasedInAGivenYear(LocalDate releaseDate) {
        bookRepository.findByReleaseDateNot(releaseDate).forEach(System.out::println);
    }

    @Override
    public void findBookBeforeReleasedDate(LocalDate localDate) {
        bookRepository.findByReleaseDateBefore(localDate).forEach(System.out::println);
    }

    @Override
    public void findBookTitleWhichContains(String containsString) {
        bookRepository.findByTitleContaining(containsString.toLowerCase()).forEach(System.out::println);
    }

    @Override
    public void findBookTitleWrittenByAuthorLastNameStartingWith(String startsWith) {
        bookRepository.findByAuthorLastNameStartingWith(startsWith).forEach(System.out::println);
    }

    @Override
    public void countOfBooksWhichTitleIsLongerThan(int symbols) {
        System.out.println(bookRepository.countByBooksWhichTitleIsLongerThan(symbols));
    }

    @Override
    public void countOfBookCopiesByAuthor() {
        bookRepository.countByBookCopiesByAuthor().forEach(System.out::println);
    }

    @Override
    public void findTheGivenBook(String title) {
        System.out.println(bookRepository.findByTitleAndRetrieveTitleEditionTypeAgeRestrictionAndPriceOnly(title));
    }

    @Override
    public void increaseBookCopiesOfAllBooksReleasedAfter(LocalDate releaseDate, int copies) {
        int increased = bookRepository.increaseBookCopiesReleasedAfter(releaseDate, copies);
        System.out.printf("%d books are released after %d %d %d, so total of %d book copies were added",
                increased, releaseDate.getYear(), releaseDate.getMonthValue(), releaseDate.getDayOfYear(),increased * copies
        );
    }

    @Override
    public void removeBooksWhichCopiesAreLowerThan(int copies) {
        bookRepository.deleteByCopiesLessThan(copies);
    }



}
