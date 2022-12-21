package com.cookiebros.libmvc.services;

import com.cookiebros.libmvc.models.BookInst;
import com.cookiebros.libmvc.models.Person;

import java.util.List;
import java.util.Optional;

public interface BooksInstService {

    void save(BookInst savedBookInst);
    void update(int bookInstId, BookInst updatedBookInst);
    void delete(int bookInstId);

    Optional<BookInst> findById(int bookInstId);
    List<BookInst> findByBookId(int bookId);
    List<BookInst> findByOwner(Person Owner);

    void addOwner(int bookInstId, Person owner);
    void removeOwner(int bookInstId);
}
