package com.cookiebros.libmvc.services;

import com.cookiebros.libmvc.models.Author;
import com.cookiebros.libmvc.models.Book;
import com.cookiebros.libmvc.models.BookInfo;
import com.cookiebros.libmvc.models.BookRating;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BooksService {
    void save(Book savedBook, BookInfo bookInfo);
    void update(int id, Book updatedBook);
    void delete(int id);
    List<Book> findAll(Integer pageNumber, Integer pageCount, String sort);
    Book findById(int id);
    Optional<Book> findOne(String title, Author author, int yearOfPublishing);
    List<Book> findByTitleStartingWith(String query);
}
