package com.cookiebros.libmvc.services;

import com.cookiebros.libmvc.models.BookInfo;
import com.cookiebros.libmvc.repositories.BooksInfoRepository;
import com.cookiebros.libmvc.repositories.BooksRepository;
import com.cookiebros.libmvc.util.Genres;
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
    //CRUD
    //CRUD
    @Override
    @Transactional
    public void update(int id, BookInfo updatedBookInfo) {
        updatedBookInfo.getBook().setId(id);
        booksInfoRepository.save(updatedBookInfo);
    }



    //
    //FIND
    //
    @Override
    public BookInfo findById(int id) {
        return booksInfoRepository.findById(id).orElse(null);
    }

    @Override
    public List<BookInfo> findByGenres(Genres genre) {
        return booksInfoRepository.findByGenres(genre);
    }

    @Override
    public List<BookInfo> findByPublisherStartingWith(String publisher) {
        return booksInfoRepository.findByPublisherStartingWith(publisher);
    }



    //
    //OTHER
    //
    @Override
    public void addGenre(int id, Genres genre) {
        BookInfo bookInfo = booksInfoRepository.findById(id).orElse(null);
        if (bookInfo != null)
            bookInfo.getGenres().add(genre);
    }
    @Override
    public void removeGenre(int id, Genres genre) {
        BookInfo bookInfo = booksInfoRepository.findById(id).orElse(null);
        if (bookInfo != null)
            bookInfo.getGenres().remove(genre);
    }
}
