package com.spring.services.impl;

import com.spring.entities.*;
import com.spring.repositories.AuthorRepository;
import com.spring.repositories.BookRepository;
import com.spring.repositories.CategoryRepository;
import com.spring.services.BookService;
import com.spring.util.FileUtil;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private final static String BOOK_FILE_PATH =
            "C:\\Users\\User\\springdataintro\\src\\main\\resources\\books.txt";

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedBooks() throws IOException {

        if (this.bookRepository.count() != 0) {
            return;
        }

        String[] books = this.fileUtil.FileContent(BOOK_FILE_PATH);

        for (String b : books) {
            String[] params = b.split("\\s+");

            Book book = new Book();

            book.setAuthor(randomAuthor());

            EditionType editionType = EditionType.values()[Integer.parseInt(params[0])];
            book.setEditionType(editionType);

            LocalDate releaseDate = LocalDate.parse(params[1],
                    DateTimeFormatter.ofPattern("d/M/yyyy"));
            book.setReleaseDate(releaseDate);


            book.setCopies(Integer.parseInt(params[2]));

            book.setPrice(new BigDecimal(params[3]));

            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(params[4])];
            book.setAgeRestriction(ageRestriction);

            StringBuilder title = new StringBuilder();

            for (int i = 5; i <= params.length - 1; i++) {
                title.append(params[i]).append(" ");

            }

            book.setTitle(title.toString().trim());

            book.setCategories(randomCategories());

            this.bookRepository.saveAndFlush(book);
        }

    }

    @Override
    public List<String> findAllTitles() {

        LocalDate releaseDate = LocalDate.parse("31/12/2000",
                DateTimeFormatter.ofPattern("d/M/yyyy"));
        return this.bookRepository
                .findAllByReleaseDateAfter(releaseDate)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    private Author randomAuthor() {
        Random random = new Random(1);

        int index = random.nextInt((int) this.authorRepository.count()) + 1;
        return this.authorRepository.getOne(index);
    }

    private Category randomCategory() {
        Random random = new Random(1);

        int index = random.nextInt((int) this.categoryRepository.count()) + 1;
        return this.categoryRepository.getOne(index);
    }

    private Set<Category> randomCategories() {
        Set<Category> categories = new LinkedHashSet<>();

        Random random = new Random();

        int index = random.nextInt((int) this.categoryRepository.count()) + 1;

        for (int i = 0; i < index; i++) {
            categories.add(randomCategory());
        }

        return categories;
    }
}
