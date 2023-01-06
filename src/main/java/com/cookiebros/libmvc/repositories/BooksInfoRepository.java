package com.cookiebros.libmvc.repositories;

import com.cookiebros.libmvc.models.Book;
import com.cookiebros.libmvc.models.BookInfo;
import com.cookiebros.libmvc.models.Genre;
import com.cookiebros.libmvc.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksInfoRepository extends JpaRepository<BookInfo, Integer> {

    Optional<BookInfo> findByBookId(int id);
//    List<BookInfo> findByGenre(Genre genre);
    List<BookInfo> findByPublisher(Publisher publisher);
//    List<BookInfo> findByPublisherStartingWith(String publisher);
}
