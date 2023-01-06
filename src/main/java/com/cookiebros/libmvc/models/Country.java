package com.cookiebros.libmvc.models;

import jakarta.persistence.*;

import java.util.List;



@Entity
@Table(name = "Country")
public class Country {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "country_name")
    String name;



    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<Author> authors;
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<Publisher> publishers;





    public Country(){}
    public Country(String name) {
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
    public List<Author> getAuthors() {
        return authors;
    }
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
    public List<Publisher> getPublishers() {
        return publishers;
    }
    public void setPublishers(List<Publisher> publishers) {
        this.publishers = publishers;
    }





    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
