package com.cookiebros.libmvc.services;

import com.cookiebros.libmvc.models.BookInst;
import com.cookiebros.libmvc.models.Person;

import java.util.List;
import java.util.Optional;

public interface BooksInstService {

    void save(BookInst savedBookInst);
    void delete(int bookInstId);

    BookInst findById(int bookInstId);
    List<BookInst> findByBookId(int bookId);
    List<BookInst> findByOwner(Person owner);

    void assignInst(int bookInstId, Person owner);
    void releaseInst(int bookInstId);
}
