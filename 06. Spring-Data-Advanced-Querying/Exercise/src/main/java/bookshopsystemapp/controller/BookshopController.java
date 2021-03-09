package bookshopsystemapp.controller;

import bookshopsystemapp.service.AuthorService;
import bookshopsystemapp.service.BookService;
import bookshopsystemapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Set;

@Controller
public class BookshopController implements CommandLineRunner {

    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;

    @Autowired
    public BookshopController(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... strings) throws Exception {
//        this.authorService.seedAuthors();
//        this.categoryService.seedCategories();
//        this.bookService.seedBooks();

      //this.printBookTitlesWithAgeRestriction();
        //this.printBookTitlesWithLessThan5000Copies();

        this.printBookTitlesWithPriceLessThan5AndGreaterThan40();


    }

    public void printBookTitlesWithAgeRestriction(){
        List<String> titles = this.bookService.getAllBookTitlesWithAgeRestriction();

        for (String title : titles) {
            System.out.println(title);
        }
    }

    public void printBookTitlesWithLessThan5000Copies(){
        List<String> titles = this.bookService.getAllBookTitlesWithLess5000Copies();

        titles.forEach(System.out::println);
    }

    public void printBookTitlesWithPriceLessThan5AndGreaterThan40(){
        List<String> titles = this.bookService.getAllBooksWithPriceLessThan5AndGreaterThan40();

        titles.forEach(System.out::println);
    }
}
