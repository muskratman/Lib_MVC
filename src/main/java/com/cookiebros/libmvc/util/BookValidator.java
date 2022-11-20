package com.cookiebros.libmvc.util;

import com.cookiebros.libmvc.dao.BookDAO;
import com.cookiebros.libmvc.models.Book;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {

    private final BookDAO bookDAO;

    public BookValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
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

    }
}