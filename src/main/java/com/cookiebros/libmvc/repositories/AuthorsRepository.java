package com.cookiebros.libmvc.repositories;

import com.cookiebros.libmvc.models.Author;
import com.cookiebros.libmvc.models.Country;
import com.cookiebros.libmvc.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, Integer> {
    List<Author> findByFullNameStartingWithIgnoreCase(String startingWith);
    List<Author> findByFullNameContainsIgnoreCase (String startingWith);
    List<Author> findByCountry(Country country);
}

