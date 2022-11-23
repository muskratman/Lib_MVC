package com.cookiebros.libmvc.repositories;

import com.cookiebros.libmvc.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findByTitleAndAuthorAndYearOfPublishing(String title, String author, int yearOfPublishing);

}
