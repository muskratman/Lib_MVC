package com.cookiebros.libmvc.services;

import com.cookiebros.libmvc.models.Book;
import com.cookiebros.libmvc.models.Person;
import com.cookiebros.libmvc.repositories.BooksRepository;
import com.cookiebros.libmvc.repositories.PeopleRepository;
import org.postgresql.gss.GSSOutputStream;
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

    public Person findByFio(String fio) {
        return peopleRepository.findByFio(fio).orElse(null);
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





//    public List<Book> showReaderBooks(int id) {
//        Person person = peopleRepository.findById(id).orElse(null);
//        return (person != null)? booksRepository.findByOwner(person): null;
//    }

    public Map<Book, Boolean> showReaderBooks(int id) {
        Map<Book, Boolean> booksMap = new LinkedHashMap<>();
        Date currentDate = new Date();
        long millis;

        Person person = peopleRepository.findById(id).orElse(null);

        for (Book book : booksRepository.findByOwner(person)) {
            millis = currentDate.getTime() - book.getOwningDate().getTime();
            booksMap.put(book, (millis > (10 * 24 * 60 * 60 * 1000)));
        }

        return booksMap;
    }
}