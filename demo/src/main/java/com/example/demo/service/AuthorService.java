package com.example.demo.service;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Category;

import java.util.List;

public interface AuthorService {
    List<Author> getAll();
    Author findById(Long id);
    void deleteById(Long id);
    Author save(String name, String surname, Long countryId);
}
