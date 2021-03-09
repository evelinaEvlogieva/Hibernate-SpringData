package com.json.exersice.services.impl;

import com.json.exersice.domain.dtos.CategorySeedDto;
import com.json.exersice.domain.entities.Category;
import com.json.exersice.repositories.CategoryRepository;
import com.json.exersice.services.CategoryService;
import com.json.exersice.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(ValidatorUtil validatorUtil, ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories(CategorySeedDto[] categorySeedDtos) {
        for (CategorySeedDto categorySeedDto : categorySeedDtos) {
            if (!validatorUtil.isValid(categorySeedDto)) {
                this.validatorUtil.violations(categorySeedDto)
                        .forEach(v -> System.out.println(v.getMessage()));

                continue;
            }

            Category category = this.modelMapper.map(categorySeedDto, Category.class);

            this.categoryRepository.saveAndFlush(category);
        }

    }
}
