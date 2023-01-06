package com.cookiebros.libmvc.services;

import com.cookiebros.libmvc.models.*;
import com.cookiebros.libmvc.repositories.BooksInfoRepository;
import com.cookiebros.libmvc.repositories.BooksRatingRepository;
import com.cookiebros.libmvc.repositories.BooksRepository;
import com.cookiebros.libmvc.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class BooksServiceImpl implements BooksService{
    private final BooksRepository booksRepository;
    private final BooksInfoRepository booksInfoRepository;
    private final BooksRatingRepository booksRatingRepository;

    @Autowired
    public BooksServiceImpl(BooksRepository booksRepository, PeopleRepository peopleRepository, BooksInfoServiceImpl booksInfoService, BooksRatingServiceImpl booksRatingService, BooksInfoRepository booksInfoRepository, BooksRatingRepository booksRatingRepository) {
        this.booksRepository = booksRepository;
        this.booksInfoRepository = booksInfoRepository;
        this.booksRatingRepository = booksRatingRepository;
    }


    //CRUD
    //CRUD
    //CRUD
    @Override
    @Transactional
    public void save(Book savedBook, BookInfo bookInfo) {
        booksRepository.save(savedBook);

        bookInfo.setBook(savedBook);
        booksInfoRepository.save(bookInfo);
    }
    @Override
    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }
    @Override
    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }



    //
    //FIND
    //
//    @Override
//    public List<Book> findAll() {
//        return booksRepository.findAll();
//    }

//    public Page<Book> findAll(Pageable pageable) {
//        return booksRepository.findAll(pageable);
//    }

    @Override
    public List<Book> findAll(Integer pageNumber, Integer perPageCount, String sort) {
        pageNumber = 0;
        perPageCount = 20;
        if (sort != null && sort.equals("year")) {
            sort = "yearOfPublishing";
        } else {
            sort = "title";
        }
        return booksRepository.findAll(PageRequest.of(pageNumber, perPageCount,
                                                Sort.by(sort)))
                                                .getContent();
    }

    @Override
    public Book findById(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<Book> findOne(String title, Author author, int yearOfPublishing) {
        return booksRepository.findByTitleAndAuthorAndYearOfPublishing(title, author, yearOfPublishing);
    }

    @Override
    public List<Book> findByTitleStartingWith(String query) {
        if (query == null || query.isEmpty()) {
            return Collections.emptyList();
        } else {
            return booksRepository.findByTitleStartingWithIgnoreCase(query);
        }
    }

}
