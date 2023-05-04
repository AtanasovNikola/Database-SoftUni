package com.example.demo.repositories;

import com.example.demo.entities.AgeRestriction;
import com.example.demo.entities.Book;
import com.example.demo.entities.EditionType;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByReleaseDateAfter(LocalDate date);

    Set<Book> findByAgeRestriction(AgeRestriction ageRestriction);

    Set<Book> findByEditionTypeAndCopiesLessThan(EditionType gold, int copies);

    @Query(value = "SELECT b FROM books AS b " +
            "WHERE b.price < :lowerThan OR b.price > :higherThan")
    Set<Book> findByPriceLessThanGreaterThan(BigDecimal lowerThan, BigDecimal higherThan);


    Set<Book> findByReleaseDateNot(LocalDate releaseDate);

    Set<Book> findByReleaseDateBefore(LocalDate localDate);


    Set<Book> findByTitleContaining(String containsString);

    Set<Book> findByAuthorLastNameStartingWith(String startsWith);

    @Query(value = "SELECT COUNT(*) FROM books AS b" +
            " WHERE LENGTH(title) > :titleLength")
    int countByBooksWhichTitleIsLongerThan(@Param("titleLength") int symbols);

    @Query(value = "SELECT COUNT(*) AS c, concat(a.firstName, ' ', a.lastName) " +
            "FROM books b " +
            "JOIN b.author a " +
            "GROUP BY concat(a.firstName, ' ', a.lastName) " +
            "ORDER BY c DESC")
    Set<String> countByBookCopiesByAuthor();

    @Query(value = "SELECT b.title, b.editionType, b.ageRestriction, b.price" +
            " FROM books AS b" +
            " WHERE b.title LIKE :title")
    String findByTitleAndRetrieveTitleEditionTypeAgeRestrictionAndPriceOnly(String title);

    @Modifying
    @Query(value = "UPDATE books b SET b.copies = b.copies + :copies " +
            "WHERE b.releaseDate > :releaseDate")
    int increaseBookCopiesReleasedAfter(LocalDate releaseDate, int copies);

    int deleteByCopiesLessThan(int copies);



}
