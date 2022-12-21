package com.cookiebros.libmvc.repositories;

import com.cookiebros.libmvc.models.Book;
import com.cookiebros.libmvc.models.BookInfo;
import com.cookiebros.libmvc.util.Genres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksInfoRepository extends JpaRepository<BookInfo, Integer> {
    List<BookInfo> findByGenres(Genres genre);
    List<BookInfo> findByPublisher(String publisher);
    List<BookInfo> findByPublisherStartingWith(String publisher);
}
