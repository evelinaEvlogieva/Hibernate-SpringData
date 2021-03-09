package com.json.exersice.services;

import com.json.exersice.domain.dtos.CategorySeedDto;

public interface CategoryService {

    void seedCategories(CategorySeedDto[] categorySeedDtos);

    interface ProductService {
    }
}
