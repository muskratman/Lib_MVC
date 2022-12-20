package com.cookiebros.libmvc.services;

import com.cookiebros.libmvc.models.*;
import com.cookiebros.libmvc.models.BookInfo;
import com.cookiebros.libmvc.repositories.BooksInfoRepository;
import com.cookiebros.libmvc.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksInfoService {
    private final BooksInfoRepository booksInfoRepository;
    private final BooksRepository booksRepository;

    @Autowired
    public BooksInfoService(BooksInfoRepository booksInfoRepository, BooksRepository booksRepository) {
        this.booksInfoRepository = booksInfoRepository;
        this.booksRepository = booksRepository;
    }

    //CRUD
    //CRUD
    //CRUD
    @Transactional
    public void update(int id, BookInfo updatedBookInfo) {
        updatedBookInfo.getBook().setId(id);
        booksInfoRepository.save(updatedBookInfo);
    }
    //
    //FIND
    //


    public BookInfo findById(int id) {
        return booksInfoRepository.findById(id).orElse(null);
    }

    public List<BookInfo> findByGenres(String genres) {
        return null;
    }



    public List<BookInfo> findByPublisherStartingWith(String publisher) {
        return null;
    }
    //
    //OTHER
    //
    public void addGenre(int id, String genre) {
        BookInfo bookInfo = booksInfoRepository.findById(id).orElse(null);

    }

    public void removeGenre(String genre) {

    }
}
