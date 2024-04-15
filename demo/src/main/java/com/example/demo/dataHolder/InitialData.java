package com.example.demo.dataHolder;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.model.Country;
import com.example.demo.repository.AuthorRepo;
import com.example.demo.repository.BookRepo;
import com.example.demo.repository.CountryRepo;
import jakarta.annotation.PostConstruct;
import org.hibernate.annotations.processing.SQL;
import org.springframework.stereotype.Component;

@Component
public class InitialData {

    private final CountryRepo countryRepo;
    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;


    public InitialData(CountryRepo countryRepo, AuthorRepo authorRepo, BookRepo bookRepo) {
        this.countryRepo = countryRepo;
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
    }

    @PostConstruct
    void init(){
        Country c1 = countryRepo.save(new Country("France", "Europe"));
        Country c2 = countryRepo.save(new Country("China", "Asia"));
        Country c3 = countryRepo.save(new Country("Egypt", "Africa"));



        Author a1 = authorRepo.save(new Author("Author1", "Surname1", c1));
        Author a2 = authorRepo.save(new Author("Author2", "Surname2", c2));
        Author a3 = authorRepo.save(new Author("Author3", "Surname3", c3));

        Book b1 = bookRepo.save(new Book("Book1", Category.BIOGRAPHY, a1, 23));
        Book b2 = bookRepo.save(new Book("Book2", Category.DRAMA, a1, 3));
        Book b3 = bookRepo.save(new Book("Book3", Category.HISTORY, a3, 1));
        test();
    }
    @SQL(value = "CREATE VIEW books_per_author AS SELECT author_id, count(id) num_books FROM book GROUP BY author_id")
    void test(){

    }
}
