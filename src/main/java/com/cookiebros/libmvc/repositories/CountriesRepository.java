package com.cookiebros.libmvc.repositories;

import com.cookiebros.libmvc.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountriesRepository extends JpaRepository<Country, Integer> {
    Optional<Country> findByName(String name);
}
