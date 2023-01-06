package com.cookiebros.libmvc.services;

import com.cookiebros.libmvc.models.Book;
import com.cookiebros.libmvc.models.BookInfo;
import com.cookiebros.libmvc.models.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    void save(Country savedCountry);
    void update(int id, Country updatedCountry);
    void delete(int id);
    List<Country> findAll();
    Country findById(int id);
    Optional<Country> findByName(String name);
}
