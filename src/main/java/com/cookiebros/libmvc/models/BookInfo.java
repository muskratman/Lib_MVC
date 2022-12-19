package com.cookiebros.libmvc.models;

import com.cookiebros.libmvc.util.SizeFormats;
import jakarta.persistence.*;

import java.io.Serializable;

//CREATE TABLE Book_Info(
//    book_id int PRIMARY KEY REFERENCES Book(id) ON DELETE CASCADE,
//    genres varchar(255),
//    publisher varchar(100),
//    sheets smallint CHECK (sheets > 0 and sheets < 10000),
//    size_format varchar(20),
//    short_description varchar(1500),
//    description varchar(2500),
//    rating_count int CHECK (rating_count > -1) DEFAULT 0,
//    rating_score int CHECK (rating_score > -1) DEFAULT 0
//);


@Entity
@Table(name = "Book_Info")
public class BookInfo implements Serializable {
    @Id
    @OneToOne()
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;
    @Column(name = "genres")
    private String genres;
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "sheets")
    private int sheets;
    @Column(name = "size_format")
    private SizeFormats sizeFormat;
    @Column(name = "short_description")
    private String shortDescription;
    @Column(name = "description")
    private String description;
    @Column(name = "rating_count")
    private int ratingCount;
    @Column(name = "rating_score")
    private int ratingScore;

    public BookInfo(){}

    public BookInfo(Book book) {
        this.book = book;
    }



    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public String getGenres() {
        return genres;
    }
    public void setGenres(String genres) {
        this.genres = genres;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public int getSheets() {
        return sheets;
    }
    public void setSheets(int sheets) {
        this.sheets = sheets;
    }
    public SizeFormats getSizeFormat() {
        return sizeFormat;
    }
    public void setSizeFormat(SizeFormats sizeFormat) {
        this.sizeFormat = sizeFormat;
    }
    public String getShortDescription() {
        return shortDescription;
    }
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getRatingCount() {
        return ratingCount;
    }
    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }
    public int getRatingScore() {
        return ratingScore;
    }
    public void setRatingScore(int ratingScore) {
        this.ratingScore = ratingScore;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "book=" + book.getTitle() +
                ", genres='" + genres + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}
