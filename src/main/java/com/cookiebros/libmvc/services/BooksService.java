package com.cookiebros.libmvc.services;

import com.cookiebros.libmvc.models.Book;
import com.cookiebros.libmvc.models.Person;
import com.cookiebros.libmvc.repositories.BooksRepository;
import com.cookiebros.libmvc.repositories.PeopleRepository;
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
    private final PeopleRepository peopleRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }


    public List<Book> findAll(Integer pageNumber, Integer pageCount, Boolean sortByYear) {

        if (pageNumber != null && pageCount != null && sortByYear == null) {
            return booksRepository.findAll(PageRequest.of(pageNumber, pageCount)).getContent();
        } else if (pageNumber == null && pageCount == null && (sortByYear != null && sortByYear)) {
            return booksRepository.findAll(Sort.by("yearOfPublishing"));
        } else if (pageNumber != null && pageCount != null && sortByYear) {
            return booksRepository.findAll(
                                    PageRequest.of(pageNumber, pageCount,
                                    Sort.by("yearOfPublishing")))
                                    .getContent();
        }
        return booksRepository.findAll();
    }

    public Book findById(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    public List<Book> findByOwner(Person owner) {
        return booksRepository.findByOwner(owner);
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


    @Transactional
    public void save(Book savedBook) {
        booksRepository.save(savedBook);
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
            reader.getBooks().remove(book);
            book.get().setOwner(null);
            book.get().setOwningDate(null);
        }
    }

    @Transactional
    public List<Book> getBooksByReaderId(int readerId) {
        Optional<Person> person = peopleRepository.findById(readerId);
        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());
            return person.get().getBooks();
        } else {
            return Collections.emptyList();
        }
    }


}
