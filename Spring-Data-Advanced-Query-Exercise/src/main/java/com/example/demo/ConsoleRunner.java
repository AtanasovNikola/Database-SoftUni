package com.example.demo;

import com.example.demo.services.AuthorService;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final BookService bookService;

    private final AuthorService authorService;

    @Autowired
    public ConsoleRunner(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        //1.bookService.findByAgeRestriction(AgeRestriction.TEEN);
        //2.bookService.findTheGoldenEditionBooksWhichHaveLessThan5000Copies(EditionType.GOLD,5000);
        //3.bookService.findBooksByPrice(BigDecimal.valueOf(5),BigDecimal.valueOf(40));
        //4.bookService.findBooksNotReleasedInAGivenYear(LocalDate.ofYearDay(1999,1));
        // 5.bookService.findBookBeforeReleasedDate(LocalDate.of(1992, 4,12));
        //6.authorService.findBookWithAuthorNameEndingWith("e");
        //7.bookService.findBookTitleWhichContains("WOR");
        //8.bookService.findBookTitleWrittenByAuthorLastNameStartingWith("gr");
        //9.bookService.countOfBooksWhichTitleIsLongerThan(12);
        //10.bookService.countOfBookCopiesByAuthor();
        //11.bookService.findTheGivenBook("Things Fall Apart");
        //12.bookService.increaseBookCopiesOfAllBooksReleasedAfter(LocalDate.of(2005,10,12),100);
        //13.bookService.removeBooksWhichCopiesAreLowerThan(210);
       // bookService.countBooksOfAuthor("Roger", "Porter");
    }
}