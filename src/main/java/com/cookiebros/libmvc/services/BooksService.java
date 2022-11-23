package com.cookiebros.libmvc.services;

import com.cookiebros.libmvc.models.Book;
import com.cookiebros.libmvc.models.Person;
import com.cookiebros.libmvc.repositories.BooksRepository;
import com.cookiebros.libmvc.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

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

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findById(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    public Optional<Book> findOne(String title, String author, int yearOfPublishing) {
        return booksRepository.findByTitleAndAuthorAndYearOfPublishing(title, author, yearOfPublishing);
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
    public void addOwner(int id, int personId) {
        Book book = booksRepository.findById(id).orElse(null);
        Person person = peopleRepository.findById(personId).orElse(null);
        try {
            book.setOwner(person);
            person.addBook(book);
        } catch (NullPointerException e) {
            System.out.println("Книга id=" + id + " или Читатель id=" + personId + "  не найден" );
        }
    }

    @Transactional
    public void removeOwner(int id) {
        Book book = booksRepository.findById(id).orElse(null);
        try {
            Person person = book.getOwner();
            book.setOwner(null);
            person.getBooks().remove(book);
        } catch (NullPointerException e) {
            System.out.println("Книга id=" + id + "  не найдена");
        }
    }

    @Transactional
    public List<Book> showReaderBooks(int personId) {
        Person person = peopleRepository.findById(personId).orElse(null);
        List<Book> books = null;
        try {
            books = person.getBooks();
        } catch (NullPointerException e) {
            System.out.println("Читатель id=" + personId + "  не найден");
        }
        return books;
    }
}
