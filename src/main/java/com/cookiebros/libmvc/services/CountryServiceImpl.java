package com.cookiebros.libmvc.services;

import com.cookiebros.libmvc.models.Country;
import com.cookiebros.libmvc.repositories.CountriesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CountryServiceImpl implements CountryService{
    private final CountriesRepository countriesRepository;

    public CountryServiceImpl(CountriesRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    @Override
    @Transactional
    public void save(Country savedCountry) {
        countriesRepository.save(savedCountry);
    }
    @Override
    @Transactional
    public void update(int id, Country updatedCountry) {
        updatedCountry.setId(id);
        countriesRepository.save(updatedCountry);
    }

    @Override
    @Transactional
    public void delete(int id) {
        countriesRepository.deleteById(id);
    }



    @Override
    public List<Country> findAll() {
        return countriesRepository.findAll();
    }

    @Override
    public Country findById(int id) {
        return countriesRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<Country> findByName(String name) {
        return countriesRepository.findByName(name);
    }
}
