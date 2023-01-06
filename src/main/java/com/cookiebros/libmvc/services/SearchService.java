package com.cookiebros.libmvc.services;

import com.cookiebros.libmvc.models.Author;
import com.cookiebros.libmvc.models.Book;

import java.util.List;
import java.util.Set;

public interface SearchService {
    Set<Book> findByTitle(String title);
    Set<Book> findByAuthor(String author);
    Set<Book> findByGenre(String genre);


    Set<Book> findByYearOfPublishing(String yearOfPublishing);
    List<Book> findByPublisherStaringWith(String startingWith);


    Set<Book> findBooks(String startingWith);
    Set<Author> findAuthors(String startingWith);
}
