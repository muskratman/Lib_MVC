package com.cookiebros.libmvc.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Genre")
public class Genre {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "genre_name")
    String name;


    @OneToMany(mappedBy = "mainGenre", fetch = FetchType.LAZY)
    private List<Book> books;




    public Genre() {}
    public Genre(String name) {
        this.name = name;
    }




    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }


    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
