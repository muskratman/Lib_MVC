package com.cookiebros.libmvc.models;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.CodePointLength;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
//    TODO
//    добавить в БД поля email и password
//
//    добавить активацию акка по email
//    https://www.youtube.com/watch?v=yBXs_gtSmUc&ab_channel=letsCode

//    добавить активацию акка через Google
//    https://www.youtube.com/watch?v=-ohlXEJeRX8&t=630s&ab_channel=letsCode

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fio")
    private String fio;
    @Column(name = "year_of_birth")
    private int yearOfBirth;

//    @Column(name = "email")
//    private String email;
//    @Column(name = "password")
//    private String password;
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person () {}

    public Person(String fio, int yearOfBirth) {
        this.fio = fio;
        this.yearOfBirth = yearOfBirth;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFio() {
        return fio;
    }
    public void setFio(String fio) {
        this.fio = fio;
    }
    public int getYearOfBirth() {
        return yearOfBirth;
    }
    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        if (getBooks().isEmpty())
            setBooks(new ArrayList<>());
        books.add(book);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }
}