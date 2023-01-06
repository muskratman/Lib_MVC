package com.cookiebros.libmvc.services;

import com.cookiebros.libmvc.models.Book;
import com.cookiebros.libmvc.models.BookInst;
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
public class PeopleServiceImpl implements PeopleService{
    private final PeopleRepository peopleRepository;
    private final BooksRepository booksRepository;

    @Autowired
    public PeopleServiceImpl(PeopleRepository peopleRepository, BooksRepository booksRepository) {
        this.peopleRepository = peopleRepository;
        this.booksRepository = booksRepository;
    }


    //CRUD
    //CRUD
    //CRUD
    @Override
    @Transactional
    public void save(Person savedPerson) {
        peopleRepository.save(savedPerson);
    }
    @Override
    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }
    @Override
    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }



    //FIND
    //FIND
    //FIND
    @Override
    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    @Override
    public Person findById(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<Person> findByFio(String fio) {
//        return peopleRepository.findByFio(fio);
        return null;
    }


    @Override
    public List<BookInst> getBooksInstByPersonId(int personId) {
        Optional<Person> reader = peopleRepository.findById(personId);

        if (reader.isPresent()) {
            Hibernate.initialize(reader.get().getBooksInst());

            reader.get().getBooksInst().forEach(bookInst -> {
                long millis = new Date().getTime() - bookInst.getOwningDate().getTime();
                bookInst.setOverdue(millis > (10 * 24 * 60 * 60 * 1000)); // 10 дней
            });

            return reader.get().getBooksInst();
        } else {
            return Collections.emptyList();
        }
    }
}