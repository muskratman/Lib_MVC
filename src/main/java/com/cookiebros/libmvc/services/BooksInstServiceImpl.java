package com.cookiebros.libmvc.services;

import com.cookiebros.libmvc.models.Book;
import com.cookiebros.libmvc.models.Person;
import com.cookiebros.libmvc.repositories.BooksInstRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksInstServiceImpl implements BooksInstService{
    private final BooksInstRepository bookInstancesRepository;

    @Autowired
    public BooksInstServiceImpl(BooksInstRepository bookInstancesRepository) {
        this.bookInstancesRepository = bookInstancesRepository;
    }


    //CRUD
    //CRUD
    //CRUD

    @Transactional
    public void update(int id, Book updatedBook) {
        Book bookToBeUpdated = booksRepository.findById(id).get();
        updatedBook.setId(id);
        updatedBook.setOwner(bookToBeUpdated.getOwner());
        updatedBook.setOwningDate(bookToBeUpdated.getOwningDate());
        booksRepository.save(updatedBook);
    }



    //
    //FIND
    //
    //    public List<Book> findByOwner(Person owner) {
//        return booksRepository.findByOwner(owner);
//    }



    //
    //OTHER
    //
    @Transactional
    public void addOwner(int id, int readerId) {
        Book book = booksRepository.findById(id).orElse(null);
        Person person = peopleRepository.findById(readerId).orElse(null);
        try {
            book.setOwner(person);
            person.addBook(book);
            book.setOwningDate(new Date());
        } catch (NullPointerException e) {
            System.out.println("Книга id=" + id + " или Читатель id=" + readerId + "  не найден" );
        }
    }

    @Transactional
    public void removeOwner(int id) {
        Optional<Book> book = booksRepository.findById(id);
        if (book.isPresent()) {
            Person reader = book.get().getOwner();
            reader.getBookInstances().remove(book);
            book.get().setOwner(null);
            book.get().setOwningDate(null);
        }
    }

    @Transactional
    public List<Book> getBooksByReaderId(int readerId) {
        Optional<Person> person = peopleRepository.findById(readerId);
        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBookInstances());
            return person.get().getBookInstances();
        } else {
            return Collections.emptyList();
        }
    }
}
