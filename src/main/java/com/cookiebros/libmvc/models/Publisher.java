package com.cookiebros.libmvc.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Publisher")
public class Publisher {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "publisher_name")
    private String name;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;
    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
    private List<BookInfo> booksInfo;





    public Publisher() {}
    public Publisher(String name, Country country) {
        this.name = name;
        this.country = country;
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
    public Country getCountry() {
        return country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }
    public List<BookInfo> getBooksInfo() {
        return booksInfo;
    }
    public void setBooksInfo(List<BookInfo> booksInfo) {
        this.booksInfo = booksInfo;
    }


    public void addBook(BookInfo bookInfo) {
        if (booksInfo.isEmpty())
            setBooksInfo(new ArrayList<>());
        booksInfo.add(bookInfo);
    }
}
