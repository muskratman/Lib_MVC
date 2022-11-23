package com.cookiebros.libmvc.util;

import com.cookiebros.libmvc.models.Book;
import com.cookiebros.libmvc.services.BooksService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class BookValidator implements Validator {

    private final BooksService booksService;

    public BookValidator(BooksService booksService) {
        this.booksService = booksService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

        Book book = (Book) target;

        //title
        if (book.getTitle().length() < 1 || book.getTitle().length() > 60)
            errors.rejectValue("title", "", "Book's title should not be empty");

        //author
        if (book.getAuthor().length() < 1 || book.getAuthor().length() > 60)
            errors.rejectValue("author", "", "Book's author should not be empty");

        //yearOfPublishing
        if (book.getYearOfPublishing() < 1600 || book.getYearOfPublishing() > 2022)
            errors.rejectValue("yearOfPublishing", "", "yearOfPublishing should be between 1600 and 2022");

        //Проверка на уникальность
        Book foundBook = booksService.findOne(book.getTitle(), book.getAuthor(),
                                                book.getYearOfPublishing()).orElse(null);
        if (foundBook != null && foundBook.getId() != book.getId()) {
            errors.rejectValue("title", "", "Такая книга уже есть");
        }
    }
}