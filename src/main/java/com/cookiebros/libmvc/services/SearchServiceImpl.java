package com.cookiebros.libmvc.services;

import com.cookiebros.libmvc.models.Author;
import com.cookiebros.libmvc.models.Book;
import com.cookiebros.libmvc.models.Genre;
import com.cookiebros.libmvc.repositories.AuthorsRepository;
import com.cookiebros.libmvc.repositories.BooksRepository;
import com.cookiebros.libmvc.repositories.GenresRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class SearchServiceImpl implements SearchService{

    private final BooksRepository booksRepository;
    private final AuthorsRepository authorsRepository;
    private final GenresRepository genresRepository;


    @Autowired
    public SearchServiceImpl(BooksRepository booksRepository, AuthorsRepository authorsRepository, GenresRepository genresRepository) {
        this.booksRepository = booksRepository;
        this.authorsRepository = authorsRepository;
        this.genresRepository = genresRepository;
    }





    @Override
    public Set<Book> findByTitle(String title) {
        Set<Book> books = new LinkedHashSet<>(Collections.emptyList());
        books.addAll(booksRepository.findByTitleStartingWithIgnoreCase(title));
        books.addAll(booksRepository.findByTitleContainsIgnoreCase(title));
        return books;
    }


    @Override
    public Set<Book> findByAuthor(String authorName) {
        Set<Book> books = new LinkedHashSet<>(Collections.emptyList());
        List<Author> authors = authorsRepository.findByFullNameStartingWithIgnoreCase(authorName);
        authors.addAll(authorsRepository.findByFullNameContainsIgnoreCase(authorName));

        for (Author author : authors) {
            books.addAll(booksRepository.findByAuthor(author));
        }
        return books;
    }

    @Override
    public Set<Book> findByGenre(String genreName) {
        Set<Book> books = new LinkedHashSet<>(Collections.emptyList());
        List<Genre> genres = genresRepository.findByNameStartingWithIgnoreCase(genreName);
        genres.addAll(genresRepository.findByNameContainsIgnoreCase(genreName));

        for (Genre genre : genres) {
            books.addAll(booksRepository.findByMainGenre(genre));
        }
        return books;
    }

    @Override
    public Set<Book> findByYearOfPublishing(String year) {
        Set<Book> books = new LinkedHashSet<>(Collections.emptyList());
        try {
            books.addAll(booksRepository.findByYearOfPublishing(Integer.parseInt(year)));
        } catch (NumberFormatException e){
            System.out.println("В поисковом запросе нет цифр");
        }
        return books;
    }


    @Override
    public Set<Book> findBooks(String searchQuery) {
        Set<Book> books = new LinkedHashSet<>(Collections.emptyList());
        books.addAll(findByTitle(searchQuery));
        books.addAll(findByAuthor(searchQuery));
        books.addAll(findByGenre(searchQuery));
        books.addAll(findByYearOfPublishing(searchQuery));
        return books;
    }

    @Override
    public Set<Author> findAuthors(String searchQuery) {
        Set<Author> authors = new LinkedHashSet<>(Collections.emptyList());
        authors.addAll(authorsRepository.findByFullNameStartingWithIgnoreCase(searchQuery));
        authors.addAll(authorsRepository.findByFullNameContainsIgnoreCase(searchQuery));
        return authors;
    }




    @Override
    public List<Book> findByPublisherStaringWith(String startingWith) {
        return null;
    }
}
