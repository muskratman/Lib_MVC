package com.cookiebros.libmvc.services;

import com.cookiebros.libmvc.models.BookInst;
import com.cookiebros.libmvc.models.Person;
import com.cookiebros.libmvc.repositories.BooksInstRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksInstServiceImpl implements BooksInstService {
    private final BooksInstRepository bookInstRepository;

    @Autowired
    public BooksInstServiceImpl(BooksInstRepository bookInstancesRepository) {
        this.bookInstRepository = bookInstancesRepository;
    }


    //CRUD
    @Override
    @Transactional
    public void save(BookInst savedBookInst) {
        //возможно надо вставить setBook  и т.д.
        bookInstRepository.save(savedBookInst);
    }
    @Override
    @Transactional
    public void delete(int bookInstId) {
        bookInstRepository.deleteById(bookInstId);
    }


    //FIND
    @Override
    public BookInst findById(int bookInstId) {
        return bookInstRepository.findById(bookInstId).orElse(null);
    }

    @Override
    public List<BookInst> findByBookId(int bookId) {
        return bookInstRepository.findByBookId(bookId);
    }

    @Override
    public List<BookInst> findByOwner(Person owner) {
        return bookInstRepository.findByOwner(owner);
    }


    //OTHER
    @Override
    @Transactional
    public void assignInst(int bookInstId, Person owner) {
        bookInstRepository.findById(bookInstId).ifPresent(
                bookInst -> {
                    bookInst.setOwner(owner);
                    bookInst.setOwningDate(new Date());
                }
        );
    }
    @Override
    @Transactional
    public void releaseInst(int bookInstId) {
        bookInstRepository.findById(bookInstId).ifPresent(
                bookInst -> {
                    bookInst.setOwner(null);
                    bookInst.setOwningDate(null);
                }
        );
    }
}