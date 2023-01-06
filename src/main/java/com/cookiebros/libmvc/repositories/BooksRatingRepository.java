package com.cookiebros.libmvc.repositories;

import com.cookiebros.libmvc.models.Book;
import com.cookiebros.libmvc.models.BookRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRatingRepository extends JpaRepository<BookRating, Book> {

}
