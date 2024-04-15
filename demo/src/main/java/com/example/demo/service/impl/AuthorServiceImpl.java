package com.example.demo.service.impl;

import com.example.demo.model.Author;
import com.example.demo.model.Country;
import com.example.demo.repository.AuthorRepo;
import com.example.demo.repository.CountryRepo;
import com.example.demo.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo authorRepo;
    private final CountryRepo countryRepo;

    public AuthorServiceImpl(AuthorRepo authorRepo, CountryRepo countryRepo) {
        this.authorRepo = authorRepo;
        this.countryRepo = countryRepo;
    }

    @Override
    public List<Author> getAll() {
        return authorRepo.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepo.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void deleteById(Long id) {
        authorRepo.deleteById(id);
    }

    @Override
    public Author save(String name, String surname, Long countryId) {

        Country country = countryRepo.findById(countryId).orElseThrow(NoSuchElementException::new);
        return authorRepo.save(new Author(name, surname, country));
    }
}
