package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.model.Country;
import com.example.demo.model.DTO.BookDTO;

import java.util.List;

public interface BookService {
    List<Book> getAll();
    Book findById(Long id);
    void deleteById(Long id);
    Book save(BookDTO bookData);

    Book update(Long bookId, BookDTO bookData);

    Book rentById(Long id);
}
