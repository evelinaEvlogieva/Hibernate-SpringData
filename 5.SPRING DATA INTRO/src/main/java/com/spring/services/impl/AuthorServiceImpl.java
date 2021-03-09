package com.spring.services.impl;

import com.spring.entities.Author;
import com.spring.repositories.AuthorRepository;
import com.spring.services.AuthorService;
import com.spring.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final static String AUTHOR_FILE_PATH =
            "C:\\Users\\User\\springdataintro\\src\\main\\resources\\authors.txt";

    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {

        if(this.authorRepository.count() != 0){
            return;
        }

        String[] authors = this.fileUtil.FileContent(AUTHOR_FILE_PATH);

        for (String s : authors) {
            String[] params = s.split("\\s+");
            Author author = new Author();
            author.setFirstName(params[0]);
            author.setLastName(params[1]);

            this.authorRepository.saveAndFlush(author);

        }

    }
}
