package com.cookiebros.libmvc.models;

import jakarta.persistence.*;

import java.util.Date;

//CREATE TABLE Book_Instance(
//        id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
//        book_id int REFERENCES Book(id) ON DELETE CASCADE,
//        person_id int REFERENCES Person(id) ON DELETE SET NULL,
//        owning_date DATE
//);


@Entity
@Table(name = "Book_Instance")
public class BookInstance {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;
    @Column(name = "owning_date")
    @Temporal(TemporalType.DATE)
    private Date owningDate;
    //проверка на просрочку возврата книги (10 дней)
    @Transient
    private boolean overdue;



    public BookInstance() {}

    public BookInstance(Book book) {
        this.book = book;
    }


    public Person getOwner() {
        return owner;
    }
    public void setOwner(Person owner) {
        this.owner = owner;
    }
    public Date getOwningDate() {
        return owningDate;
    }
    public void setOwningDate(Date owningDate) {
        this.owningDate = owningDate;
    }
    public boolean isOverdue() {
        return overdue;
    }
    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }


    @Override
    public String toString() {
        return "BookInstance{" +
                "id=" + id +
                ", book=" + book.getTitle() +
                '}';
    }
}
