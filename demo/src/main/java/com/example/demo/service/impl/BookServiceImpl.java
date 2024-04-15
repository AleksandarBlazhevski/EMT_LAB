package com.example.demo.service.impl;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.model.DTO.BookDTO;
import com.example.demo.model.exception.NoAvailableCopiesException;
import com.example.demo.repository.BookRepo;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepo bookRepo, AuthorService authorService) {
        this.bookRepo = bookRepo;
        this.authorService = authorService;
    }

    @Override
    public List<Book> getAll() {
        return bookRepo.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepo.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void deleteById(Long id) {
        bookRepo.deleteById(id);
    }

    @Override
    public Book save(BookDTO bookData) {
        Author author = authorService.findById(bookData.authorId());
        return bookRepo.save(
                new Book(bookData.name(), bookData.category(), author, bookData.availableCopies()));
    }

    @Override
    public Book update(Long bookId, BookDTO bookData) {

        Book bookToUpdate = findById(bookId);
        Author author = authorService.findById(bookData.authorId());

        bookToUpdate.setAttributes(bookData.name(), bookData.category(), author, bookData.availableCopies());
        return bookRepo.save(bookToUpdate);
    }

    @Override
    public Book rentById(Long id) {
        Book bookToRent = findById(id);
        if(bookToRent.getAvailableCopies() == 0){
            throw new NoAvailableCopiesException(bookToRent.getName());
        }
        bookToRent.rentCopy();
        return bookRepo.save(bookToRent);
    }
}
