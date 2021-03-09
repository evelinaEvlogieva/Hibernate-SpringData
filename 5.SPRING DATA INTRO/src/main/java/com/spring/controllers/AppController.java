package com.spring.controllers;

import com.spring.services.AuthorService;
import com.spring.services.BookService;
import com.spring.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.util.List;

@Controller
public class AppController implements CommandLineRunner {

    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;

    @Autowired
    public AppController(AuthorService authorService, CategoryService categoryService,
                         BookService bookService, BookService bookService1) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService1;
    }

    @Override
    public void run(String... args) throws Exception {
        this.authorService.seedAuthors();
        this.categoryService.seedCategories();
        this.bookService.seedBooks();

        List<String> titles = this.bookService.findAllTitles();

        for (String title : titles) {
            System.out.println(title);
        }

    }
}
