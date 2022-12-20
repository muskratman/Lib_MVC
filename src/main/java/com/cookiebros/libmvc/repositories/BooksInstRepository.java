package com.cookiebros.libmvc.repositories;

import com.cookiebros.libmvc.models.Book;
import com.cookiebros.libmvc.models.BookInst;
import com.cookiebros.libmvc.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksInstRepository extends JpaRepository<BookInst, Integer> {

    List<BookInst> findByOwner(Person owner);
}
