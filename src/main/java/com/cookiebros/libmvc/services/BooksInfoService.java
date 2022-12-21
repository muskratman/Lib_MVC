package com.cookiebros.libmvc.services;

import com.cookiebros.libmvc.models.BookInfo;
import com.cookiebros.libmvc.util.Genres;

import java.util.List;

public interface BooksInfoService {

    void update(int id, BookInfo updatedBookInfo);

    BookInfo findById(int id);

    List<BookInfo> findByGenres(Genres genre);

    List<BookInfo> findByPublisherStartingWith(String publisher);

    void addGenre(int id, Genres genre);

    void removeGenre(int id, Genres genre);
}
