package com.cookiebros.libmvc.repositories;

import com.cookiebros.libmvc.models.Author;
import com.cookiebros.libmvc.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenresRepository extends JpaRepository<Genre, Integer> {
    List<Genre> findByNameStartingWithIgnoreCase(String genreName);
    List<Genre> findByNameContainsIgnoreCase(String genreName);
}
