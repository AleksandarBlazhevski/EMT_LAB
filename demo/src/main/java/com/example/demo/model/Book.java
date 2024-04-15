package com.example.demo.model;

import com.example.demo.model.DTO.BookDTO;
import jakarta.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private Author author;

    private Integer availableCopies;

    public Author getAuthor() {
        return author;
    }

    public Category getCategory() {
        return category;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }


    public Book(String name, Category category, Author author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public Book() {
    }


    public void setAttributes(String name, Category category, Author author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public void rentCopy() {
        availableCopies--;
    }
}
