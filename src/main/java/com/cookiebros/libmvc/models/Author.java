package com.cookiebros.libmvc.models;

import jakarta.persistence.*;
import org.hibernate.annotations.LazyToOne;

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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;
    @Column(name = "author_bio")
    private String bio;
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Book> books;




    public Author() {}
    public Author(String fullName, int yearOfBirth, Country country, String bio) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
        this.country = country;
        this.bio = bio;
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
    public Country getCountry() {
        return country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
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
