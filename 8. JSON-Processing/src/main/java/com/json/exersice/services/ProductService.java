package com.json.exersice.services;

import com.json.exersice.domain.dtos.ProductSeedDto;

public interface ProductService {
    void seedProducts(ProductSeedDto[] productSeedDtos);
}
