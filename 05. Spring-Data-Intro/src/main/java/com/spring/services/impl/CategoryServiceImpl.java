package com.spring.services.impl;

import com.spring.entities.Category;
import com.spring.repositories.CategoryRepository;
import com.spring.services.CategoryService;
import com.spring.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final static String CATEGORY_FILE_PATH =
            "C:\\Users\\User\\springdataintro\\src\\main\\resources\\categories.txt";

    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;


    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedCategories() throws IOException {
        if (this.categoryRepository.count() != 0) {
            return;
        }

        String[] categories = this.fileUtil.FileContent(CATEGORY_FILE_PATH);

        for (String cat : categories) {
            Category category = new Category();
            category.setName(cat);

            this.categoryRepository.saveAndFlush(category);

        }

    }
}
