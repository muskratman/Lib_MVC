package com.cookiebros.libmvc.models;

import com.cookiebros.libmvc.enums.SizeFormats;
import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "Book_Info")
public class BookInfo implements Serializable {
    @Id
    @Column(name = "book_id")
    private int id;
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;


    ////////////////////////////////////////////////////////////////////////////////////////TODO
    @Column(name = "genres")
    private int[] genres;
    @Column(name = "sheets")
    private int sheets;
    @Enumerated(EnumType.STRING)
    private SizeFormats sizeFormat;
    @Column(name = "short_description")
    private String shortDescription;
    @Column(name = "description")
    private String description;
    @Column(name = "rating_count")
    private int ratingCount;
    @Column(name = "rating_score")
    private int ratingScore;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    private Publisher publisher;






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
    public int[] getGenres() {
        return genres;
    }
    public void setGenres(int[] genres) {
        this.genres = genres;
    }
    public Publisher getPublisher() {
        return publisher;
    }
    public void setPublisher(Publisher publisher) {
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
