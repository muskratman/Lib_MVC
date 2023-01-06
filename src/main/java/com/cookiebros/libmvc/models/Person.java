package com.cookiebros.libmvc.models;

import com.cookiebros.libmvc.enums.Roles;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
//    TODO
//    добавить активацию акка по email
//    https://www.youtube.com/watch?v=yBXs_gtSmUc&ab_channel=letsCode
//    добавить активацию акка через Google
//    https://www.youtube.com/watch?v=-ohlXEJeRX8&t=630s&ab_channel=letsCode

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "year_of_birth")
    private int yearOfBirth;
    @Enumerated(EnumType.STRING)
    private Roles role;


    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<BookInst> booksInst;
    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private BookRating bookRating;


    public Person () {}

    public Person(String email, String password, String firstName, String lastName, int yearOfBirth) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public int getYearOfBirth() {
        return yearOfBirth;
    }
    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
    public Roles getRole() {
        return role;
    }
    public void setRole(Roles role) {
        this.role = role;
    }



    public List<BookInst> getBooksInst() {
        return booksInst;
    }

    public void setBooksInst(List<BookInst> bookInstances) {
        this.booksInst = booksInst;
    }

//    public void addBookInst(BookInst bookInst) {
//        if (getBooksInst().isEmpty())
//            setBooksInst(new ArrayList<>());
//        booksInst.add(bookInst);
//    }





    public BookRating getBookRating() {
        return bookRating;
    }
    public void setBookRating(BookRating bookRating) {
        this.bookRating = bookRating;
    }


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + firstName + " " + lastName + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }
}