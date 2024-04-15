package com.example.demo.service;


import com.example.demo.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> getAll();
    Country findById(Long id);
    void deleteById(Long id);
    Country save(String name, String continent);
}
