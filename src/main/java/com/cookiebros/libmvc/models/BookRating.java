package com.cookiebros.libmvc.models;

import jakarta.persistence.*;


@Entity
@Table(name = "Book_Rating")
@IdClass(BookRatingId.class)
public class BookRating {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
//    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Id
    @OneToOne(fetch = FetchType.LAZY)
//    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(name = "rating")
    private int rating;



    public BookRating(){}
    public BookRating(Book book, Person person, int rating) {
        this.book = book;
        this.person = person;
        this.rating = rating;
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
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "BookRating{" +
                "book=" + book +
                ", person=" + person +
                ", rating=" + rating +
                '}';
    }
}
