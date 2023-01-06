package com.cookiebros.libmvc.services;

import com.cookiebros.libmvc.models.BookInfo;
import com.cookiebros.libmvc.models.Genre;

import java.util.List;

public interface BooksInfoService {

    void update(int bookId, BookInfo updatedBookInfo);

    BookInfo findByBookId(int bookId);

    List<BookInfo> findByGenre(String genre);

    List<BookInfo> findByPublisherStartingWith(String publisher);

    void addGenre(int bookId, Genre genre);

    void removeGenre(int bookId, Genre genre);
}
