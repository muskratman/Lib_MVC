package com.cookiebros.libmvc.models;

import jakarta.persistence.*;


//CREATE TABLE Book_Rating(
//        book_id int REFERENCES Book(id) ON DELETE CASCADE,
//        person_id int REFERENCES Person(id) ON DELETE CASCADE,
//        rating int CHECK (rating > 0 and rating < 11),
//        PRIMARY KEY (book_id, person_id)
//);

@Entity
@Table(name = "Book_Rating")
public class BookRating {
    @Id
    @OneToOne()
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;
    @OneToOne()
    @JoinColumn(name = "person_id", referencedColumnName = "id")
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
                "book=" + book.getTitle() +
                ", person=" + person +
                ", rating=" + rating +
                '}';
    }
}
