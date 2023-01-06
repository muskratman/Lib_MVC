package com.cookiebros.libmvc.services;

import com.cookiebros.libmvc.models.BookInst;
import com.cookiebros.libmvc.models.Person;
import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PeopleService {
    void save(Person savedPerson);
    void update(int id, Person updatedPerson);
    void delete(int id);
    List<Person> findAll();
    Person findById(int id);
    Optional<Person> findByFio(String fio);
    List<BookInst> getBooksInstByPersonId(int personId);
}
