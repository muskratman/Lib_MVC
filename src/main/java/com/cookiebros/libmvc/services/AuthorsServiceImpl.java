package com.cookiebros.libmvc.services;

import com.cookiebros.libmvc.models.Author;
import com.cookiebros.libmvc.models.Book;
import com.cookiebros.libmvc.models.Country;
import com.cookiebros.libmvc.repositories.AuthorsRepository;
import org.hibernate.Hibernate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AuthorsServiceImpl implements AuthorsService{
    private final AuthorsRepository authorsRepository;

    public AuthorsServiceImpl(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }


    //CRUD
    //CRUD
    //CRUD
    @Override
    @Transactional
    public void save(Author savedAuthor) {
        authorsRepository.save(savedAuthor);
    }
    @Override
    @Transactional
    public void update(int id, Author updatedAuthor) {
        updatedAuthor.setId(id);
        authorsRepository.save(updatedAuthor);
    }
    @Override
    @Transactional
    public void delete(int id) {
        authorsRepository.deleteById(id);
    }



    //FIND
    //FIND
    //FIND
    @Override
    public Author findById(int id) {
        return authorsRepository.findById(id).orElse(null);
    }

    @Override
    public List<Author> findAll() {
        return authorsRepository.findAll(Sort.by("fullName"));
    }

    @Override
    public List<Author> findByFullNameStartingWith(String startingWith) {
        return authorsRepository.findByFullNameStartingWithIgnoreCase(startingWith);
    }

    @Override
    public List<Author> findByCountry(Country country) {
        return authorsRepository.findByCountry(country);
    }

    @Override
    public List<Book> getAuthorBooks(int authorId) {
        Author author = findById(authorId);
        List<Book> books = new ArrayList<>(Collections.emptyList());
//        if (author != null) {
//            Hibernate.initialize(author.getBooks());
//            books = author.getBooks();
//        }
        return author.getBooks();
    }
}
