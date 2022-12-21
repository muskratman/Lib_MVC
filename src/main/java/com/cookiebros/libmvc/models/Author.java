package com.cookiebros.libmvc.models;

import com.cookiebros.libmvc.util.Countries;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Author")
public class Author {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "year_of_birth")
    private int yearOfBirth;
    @Column(name = "country")
    private Countries country;
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Book> books;




    public Author() {}
    public Author(String fullName, int yearOfBirth, Countries country) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
        this.country = country;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public int getYearOfBirth() {
        return yearOfBirth;
    }
    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
    public Countries getCountry() {
        return country;
    }
    public void setCountry(Countries country) {
        this.country = country;
    }


    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        if (books.isEmpty())
            setBooks(new ArrayList<>());
        books.add(book);
    }

}
