//package com.cookiebros.libmvc.dao;
//
//import com.cookiebros.libmvc.models.Book;
//import com.cookiebros.libmvc.models.Person;
//import org.hibernate.Hibernate;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.Query;
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class BookDAO {
//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public BookDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional(readOnly = true)
//    public List<Book> index() {
//        Session session = sessionFactory.getCurrentSession();
//
//        return session.createQuery("SELECT b FROM Book b", Book.class).getResultList();
//    }
//
//    @Transactional(readOnly = true)
//    public Book show(int id) {
//        Session session = sessionFactory.getCurrentSession();
//
//        return session.get(Book.class, id);
//    }
//
//    @Transactional(readOnly = true)
//    public Optional<Book> show(String title, String author, int yearOfPublishing) {
//        Session session = sessionFactory.getCurrentSession();
//
//        return session.createQuery("FROM Book WHERE " +
//                "title=:titleParam, author=:authorParam, year_of_publishing=:yearParam", Book.class).
//                setParameter("titleParam", title).
//                setParameter("authorParam", author).
//                setParameter("yearParam", yearOfPublishing).
//                stream().findAny();
//    }
//
//    @Transactional
//    public void save(Book savedBook) {
//        Session session = sessionFactory.getCurrentSession();
//        session.persist(savedBook);
//    }
//
//    @Transactional
//    public void update(int id, Book updatedBook) {
//        Session session = sessionFactory.getCurrentSession();
//        Book book = session.get(Book.class, id);
//        book.setTitle(updatedBook.getTitle());
//        book.setAuthor(updatedBook.getAuthor());
//        book.setYearOfPublishing(updatedBook.getYearOfPublishing());
//    }
//
//    @Transactional
//    public void delete(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        Book book = session.get(Book.class, id);
//        Person owner = book.getOwner();
//        if (owner != null)
//            owner.getBooks().remove(book);
//        session.remove(session.get(Book.class, id));
//    }
//
//    @Transactional
//    public void addOwner(int id, int personId) {
//        Session session = sessionFactory.getCurrentSession();
//        Book book = session.get(Book.class, id);
//        Person person = session.get(Person.class, personId);
//        book.setOwner(person);
//        person.addBook(book);
//    }
//
//    @Transactional
//    public void removeOwner(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        Book book = session.get(Book.class, id);
//        Person person = session.get(Person.class, book.getOwner().getId());
//        book.setOwner(null);
//        person.getBooks().remove(book);
//    }
//
//    @Transactional(readOnly = true)
//    public List<Book> showReadersBooks(int readerId) {
//        Session session = sessionFactory.getCurrentSession();
//        Person person = session.get(Person.class, readerId);
//        Hibernate.initialize(person.getBooks());
//        return person.getBooks();
//    }
//}