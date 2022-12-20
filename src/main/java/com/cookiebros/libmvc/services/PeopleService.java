package com.cookiebros.libmvc.services;

import com.cookiebros.libmvc.models.Book;
import com.cookiebros.libmvc.models.Person;
import com.cookiebros.libmvc.repositories.BooksRepository;
import com.cookiebros.libmvc.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final BooksRepository booksRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, BooksRepository booksRepository) {
        this.peopleRepository = peopleRepository;
        this.booksRepository = booksRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findById(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    public Optional<Person> findByFio(String fio) {
        return peopleRepository.findByFio(fio);
    }

    @Transactional
    public void save(Person savedPerson) {
        peopleRepository.save(savedPerson);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }


    public List<Book> showReaderBooks(int personId) {
        List<Book> books = new ArrayList<>(Collections.emptyList());
        Optional<Person> person = peopleRepository.findById(personId);
        Date currentDate = new Date();
        long millis;

        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBookInstances());
            books = booksRepository.findByOwner(person.get());
            for (Book book : books) {
                millis = currentDate.getTime() - book.getOwningDate().getTime();
                book.setOverdue(millis > (10 * 24 * 60 * 60 * 1000)); // 10 дней
            }
        }

        return books;
    }
}