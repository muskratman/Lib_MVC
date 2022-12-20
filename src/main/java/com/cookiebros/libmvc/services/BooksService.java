package com.cookiebros.libmvc.services;

import com.cookiebros.libmvc.models.*;
import com.cookiebros.libmvc.repositories.BooksInfoRepository;
import com.cookiebros.libmvc.repositories.BooksRatingRepository;
import com.cookiebros.libmvc.repositories.BooksRepository;
import com.cookiebros.libmvc.repositories.PeopleRepository;
import com.cookiebros.libmvc.util.Genres;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;
    private final BooksInfoRepository booksInfoRepository;
    private final BooksRatingRepository booksRatingRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository, BooksInfoService booksInfoService, BooksRatingService booksRatingService, BooksInfoRepository booksInfoRepository, BooksRatingRepository booksRatingRepository) {
        this.booksRepository = booksRepository;
        this.booksInfoRepository = booksInfoRepository;
        this.booksRatingRepository = booksRatingRepository;
    }


    private int id;
    private String title;
    private String author;
    private int yearOfPublishing;
    private Genres mainGenre;
    private Double rating;
    private BookInfo bookInfo;
    private List<BookInst> bookInsts;
    private BookRating bookRating;

    //CRUD
    //CRUD
    //CRUD
    @Transactional
    public void save(Book savedBook, BookInfo bookInfo, BookRating bookRating) {
        booksRepository.save(savedBook);

        bookInfo.setBook(savedBook);
        booksInfoRepository.save(bookInfo);

        bookRating.setBook(savedBook);
        booksRatingRepository.save(bookRating);
    }
    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }
    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }



    //
    //FIND
    //
    public List<Book> findAll(Integer pageNumber, Integer pageCount, Boolean sortByYear) {

        if (pageNumber != null && pageCount != null && sortByYear == null) {
            return booksRepository.findAll(PageRequest.of(pageNumber, pageCount)).getContent();
        } else if (pageNumber == null && pageCount == null && (sortByYear != null && sortByYear)) {
            return booksRepository.findAll(Sort.by("yearOfPublishing"));
        } else if (pageNumber != null && pageCount != null && sortByYear) {
            return booksRepository.findAll(PageRequest.of(pageNumber, pageCount,
                                            Sort.by("yearOfPublishing")))
                                            .getContent();
        }
        return booksRepository.findAll();
    }

    public Book findById(int id) {
        return booksRepository.findById(id).orElse(null);
    }


    public Optional<Book> findOne(String title, String author, int yearOfPublishing) {
        return booksRepository.findByTitleAndAuthorAndYearOfPublishing(title, author, yearOfPublishing);
    }

    public List<Book> findByTitleStartingWith(String query) {
        if (query == null || query.isEmpty()) {
            return Collections.emptyList();
        } else {
            return booksRepository.findByTitleStartingWithIgnoreCase(query);
        }
    }


    //
    //OTHER
    //







}
