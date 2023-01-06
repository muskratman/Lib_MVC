package com.cookiebros.libmvc.models;

import jakarta.persistence.*;
import org.hibernate.annotations.LazyToOne;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "year_of_publishing")
    private int yearOfPublishing;
    @Column(name = "rating")
    private Double rating;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre mainGenre;
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<BookInst> bookInstances;
    @OneToOne(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private BookInfo bookInfo;
    @OneToOne(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private BookRating bookRating;


    public Book() {}

    public Book(String title, Author author, int yearOfPublishing, Genre mainGenre, Double rating) {
        this.title = title;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
        this.mainGenre = mainGenre;
        this.rating = rating;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
    public int getYearOfPublishing() {
        return yearOfPublishing;
    }
    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }
    public Genre getMainGenre() {
        return mainGenre;
    }
    public void setMainGenre(Genre mainGenre) {
        this.mainGenre = mainGenre;
    }
    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }


    public BookInfo getBookInfo() {
        return bookInfo;
    }
    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }


    public List<BookInst> getBookInstances() {
        return bookInstances;
    }
    public void setBookInstances(List<BookInst> bookInsts) {
        this.bookInstances = bookInsts;
    }
    public void addBookInstance(BookInst bookInst) {
        if (getBookInstances().isEmpty())
            setBookInstances(new ArrayList<>());
        bookInstances.add(bookInst);
    }


    public BookRating getBookRating() {
        return bookRating;
    }
    public void setBookRating(BookRating bookRating) {
        this.bookRating = bookRating;
    }


    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}