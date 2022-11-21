package com.cookiebros.libmvc.dao;

import com.cookiebros.libmvc.models.Book;
import com.cookiebros.libmvc.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    @Transactional(readOnly = true)
    public Optional<Person> show(String fio) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Person WHERE fio=:fioParam", Person.class).
                setParameter("fioParam", fio).
                stream().findAny();
    }

    @Transactional
    public void save(Person savedPerson) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(savedPerson);
    }

    public void update(int id, Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        person.setFio(updatedPerson.getFio());
        person.setYearOfBirth(updatedPerson.getYearOfBirth());
    }

    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person owner = session.get(Person.class, id);
        if (!owner.getBooks().isEmpty()) {
            for (Book book : owner.getBooks()) {
                book.setOwner(null);
            }
        }
        session.remove(session.get(Person.class, id));
    }

}