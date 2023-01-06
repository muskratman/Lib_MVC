package com.cookiebros.libmvc.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToOne;

import java.io.Serializable;
import java.util.Objects;

public class BookRatingId implements Serializable {
    private Book book;
    private Person person;


    public BookRatingId() {}

    public BookRatingId(Book book, Person person) {
        this.book = book;
        this.person = person;
    }
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookRatingId that = (BookRatingId) o;
        return Objects.equals(book, that.book) && Objects.equals(person, that.person);
    }
    @Override
    public int hashCode() {
        return Objects.hash(book, person);
    }
}