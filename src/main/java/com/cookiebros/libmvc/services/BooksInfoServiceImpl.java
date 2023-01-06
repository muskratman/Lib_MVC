package com.cookiebros.libmvc.services;

import com.cookiebros.libmvc.models.BookInfo;
import com.cookiebros.libmvc.models.Genre;
import com.cookiebros.libmvc.repositories.BooksInfoRepository;
import com.cookiebros.libmvc.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksInfoServiceImpl implements BooksInfoService{
    private final BooksInfoRepository booksInfoRepository;
    private final BooksRepository booksRepository;

    @Autowired
    public BooksInfoServiceImpl(BooksInfoRepository booksInfoRepository, BooksRepository booksRepository) {
        this.booksInfoRepository = booksInfoRepository;
        this.booksRepository = booksRepository;
    }


    //CRUD
    @Override
    @Transactional
    public void update(int bookId, BookInfo updatedBookInfo) {
        BookInfo bookInfo = findByBookId(bookId);

        updatedBookInfo.setBook(bookInfo.getBook());
        updatedBookInfo.setRatingCount(bookInfo.getRatingCount());
        updatedBookInfo.setRatingScore(bookInfo.getRatingScore());

        booksInfoRepository.save(updatedBookInfo);
    }



    //
    //FIND
    //
    @Override
    public BookInfo findByBookId(int bookId) {
        return booksInfoRepository.findByBookId(bookId).orElse(null);
    }

    @Override
    public List<BookInfo> findByGenre(String genre) {
//        Genres searchingGenre = genre.
//        return booksInfoRepository.findByGenres(genre);
        return null;
    }

    @Override
    public List<BookInfo> findByPublisherStartingWith(String publisher) {
//        return booksInfoRepository.findByPublisherStartingWith(publisher);
        return null;
    }



    //
    //OTHER
    //
    @Override
    public void addGenre(int bookId, Genre genre) {
//        BookInfo bookInfo = booksInfoRepository.findById(bookId).orElse(null);
//        if (bookInfo != null)
//            bookInfo.getGenres().add(genre);
    }
    @Override
    public void removeGenre(int bookId, Genre genre) {
//        BookInfo bookInfo = booksInfoRepository.findById(bookId).orElse(null);
//        if (bookInfo != null)
//            bookInfo.getGenres().remove(genre);
    }
}
