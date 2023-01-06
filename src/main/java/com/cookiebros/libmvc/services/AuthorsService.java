package com.cookiebros.libmvc.services;

import com.cookiebros.libmvc.models.Author;
import com.cookiebros.libmvc.models.Book;
import com.cookiebros.libmvc.models.Country;

import java.util.List;
import java.util.Optional;

public interface AuthorsService {
    void save(Author savedAuthor);
    void update(int id, Author updatedAuthor);
    void delete(int id);

    Author findById(int id);
    List<Author> findAll();
    List<Author> findByFullNameStartingWith(String startingWith);
    List<Author> findByCountry(Country country);

    List<Book> getAuthorBooks(int authorId);
}
