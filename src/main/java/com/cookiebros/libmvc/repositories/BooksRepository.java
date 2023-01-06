package com.cookiebros.libmvc.repositories;

import com.cookiebros.libmvc.models.Author;
import com.cookiebros.libmvc.models.Book;
import com.cookiebros.libmvc.models.Genre;
import com.cookiebros.libmvc.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

//    @Query("FROM Book")
//    @Query("SELECT a FROM Book a")
    @Query(value = "SELECT b FROM Book b")
    List<Book> findAll(Integer pageNumber, Integer pageCount, String sort);

    Optional<Book> findByTitleAndAuthorAndYearOfPublishing(String title, Author author, int yearOfPublishing);

    List<Book> findByTitleStartingWithIgnoreCase(String query);
    List<Book> findByTitleContainsIgnoreCase (String titleContains);

    List<Book> findByYearOfPublishing(int year);

    List<Book> findByAuthor(Author author);
    List<Book> findByMainGenre(Genre genre);

}

//@Query("FROM Book WHERE yearOfPublishing =:#{#year}")
//List<Book> findByYearOfPublishing(@Param("year") String year);
//ИЛИ
//@Query("FROM Book WHERE CAST(yearOfPublishing AS string) LIKE CONCAT(:#{#year}, '%')")
//List<Book> findByYearOfPublishing(@Param("year") String year);