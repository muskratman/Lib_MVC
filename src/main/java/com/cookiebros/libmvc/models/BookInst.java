package com.cookiebros.libmvc.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Book_Instance")
public class BookInst {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;
    @Column(name = "owning_date")
    @Temporal(TemporalType.DATE)
    private Date owningDate;
    //проверка на просрочку возврата книги (10 дней)
    @Transient
    private boolean overdue;



    public BookInst() {}

    public BookInst(Book book) {
        this.book = book;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
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
