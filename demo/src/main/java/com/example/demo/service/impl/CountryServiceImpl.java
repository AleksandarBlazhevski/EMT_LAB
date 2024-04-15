package com.example.demo.service.impl;

import com.example.demo.model.Book;
import com.example.demo.model.Country;
import com.example.demo.repository.CountryRepo;
import com.example.demo.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepo countryRepo;

    public CountryServiceImpl(CountryRepo countryRepo) {
        this.countryRepo = countryRepo;
    }

    @Override
    public List<Country> getAll() {
        return countryRepo.findAll();
    }

    @Override
    public Country findById(Long id) {
        return countryRepo.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void deleteById(Long id) {
        countryRepo.deleteById(id);
    }

    @Override
    public Country save(String name, String continent) {
        return countryRepo.save(new Country(name, continent));
    }
}
