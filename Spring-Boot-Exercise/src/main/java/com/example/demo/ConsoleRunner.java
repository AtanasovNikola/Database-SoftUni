package com.example.demo;

import com.example.demo.entities.Author;
import com.example.demo.entities.Book;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Component
public class ConsoleRunner implements CommandLineRunner {
    @Autowired
    private SeedService seedService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;


    @Override
    public void run(String... args) throws Exception {
        this.seedService.seedAuthors();
        this.seedService.seedCategories();
        this.seedService.seedBooks();
        this.booksAfter2000();
        this.authorsWithAtLeastOneBookAndBefore1990();
        this.authorsOrderByTheNumberOfTheirBooks();
        this.getAllBooksFromAuthorGeorgePowellOrderByReleaseDateDescThenByBookTitleAsc();
    }

    private void getAllBooksFromAuthorGeorgePowellOrderByReleaseDateDescThenByBookTitleAsc() {
        Author result = authorRepository.findByFirstNameAndLastName("George", "Powell");
        Set<Book> books = result.getBooks();
        books.stream()
                .sorted(Comparator.comparing(Book::getReleaseDate).reversed()
                        .thenComparing((Book::getTitle)))
                .forEach(
                        b -> System.out.println(b.getAuthor().getLastName() + " " + b.getTitle() + " " + b.getReleaseDate() + " " + b.getCopies()));
        ;
    }

    private void authorsOrderByTheNumberOfTheirBooks() {
        List<Author> authors = authorRepository.findAll();
        authors.stream()
                .sorted((l, r) -> r.getBooks().size() - l.getBooks().size())
                .forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName() + " " + a.getBooks().size()));

    }

    private void authorsWithAtLeastOneBookAndBefore1990() {
        LocalDate localDate = LocalDate.of(1990, 1, 1);
        List<Author> result = authorRepository.findDistinctByBooksReleaseDateBefore(localDate);
        result.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
    }

    private void booksAfter2000() {
        LocalDate localDate = LocalDate.of(2000, 1, 1);
        List<Book> result = bookRepository.findByReleaseDateAfter(localDate);
        result.forEach(book -> System.out.println(book.getReleaseDate() + " " + book.getTitle()));
    }

}
