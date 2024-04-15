package com.example.demo.controller;


import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.model.DTO.BookDTO;
import com.example.demo.model.exception.NoAvailableCopiesException;
import com.example.demo.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        ResponseEntity<Book> response;

        try {
            Book book = bookService.findById(id);
            response = ResponseEntity.ok().body(book);
        } catch (NoSuchElementException e) {
            response = ResponseEntity.badRequest().build();
        }

        return response;
    }

    @GetMapping("/categories")
    public Category[] getAllCategories(){
        return Category.values();
    }
    @PostMapping("/add")
    public ResponseEntity<Book> addNewBook(@RequestBody BookDTO bookData){
        ResponseEntity<Book> response;

        try {
            Book newBook = bookService.save(bookData);
            response = ResponseEntity.ok().body(newBook);
        } catch (NoSuchElementException e) {
            response = ResponseEntity.badRequest().build();
        }

        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        ResponseEntity<Book> response;

        try {
            Book bookToDelete = bookService.findById(id);
            bookService.deleteById(id);
            response = ResponseEntity.ok().body(bookToDelete);
        } catch (NoSuchElementException e) {
            response = ResponseEntity.badRequest().build();
        }

        return response;
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookDTO bookData){
        ResponseEntity<Book> response;

        try {
            Book updatedBook = bookService.update(id, bookData);
            response = ResponseEntity.ok(updatedBook);
        } catch (Exception ex){
            response = ResponseEntity.badRequest().build();
        }

        return response;
    }

    @PostMapping("/rent/{id}")
    public ResponseEntity<Book>  rentBook(@PathVariable Long id){
        ResponseEntity<Book> response;

        try {
            Book bookToRent = bookService.rentById(id);
            response = ResponseEntity.ok().body(bookToRent);
        } catch (NoAvailableCopiesException e) {
            response = ResponseEntity.badRequest().header("Error message", e.getMessage()).build();
        }

        return response;
    }
}
