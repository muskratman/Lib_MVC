package com.cookiebros.libmvc.services;

import com.cookiebros.libmvc.models.BookRating;
import com.cookiebros.libmvc.repositories.BooksRatingRepository;
import com.cookiebros.libmvc.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BooksRatingServiceImpl {
    private final BooksRatingRepository bookRatingRepository;
    private final BooksRepository booksRepository;

    @Autowired
    public BooksRatingServiceImpl(BooksRatingRepository bookRatingRepository, BooksRepository booksRepository) {
        this.bookRatingRepository = bookRatingRepository;
        this.booksRepository = booksRepository;
    }

    //CRUD
    //CRUD
    //CRUD
    @Transactional
    public void update(int id, BookRating updatedBookRating) {
        updatedBookRating.getBook().setId(id);
        bookRatingRepository.save(updatedBookRating);
    }

}
