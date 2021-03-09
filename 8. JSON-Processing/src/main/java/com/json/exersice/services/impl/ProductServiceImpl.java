package com.json.exersice.services.impl;

import com.json.exersice.domain.dtos.ProductSeedDto;
import com.json.exersice.domain.entities.Category;
import com.json.exersice.domain.entities.Product;
import com.json.exersice.domain.entities.User;
import com.json.exersice.repositories.CategoryRepository;
import com.json.exersice.repositories.ProductRepository;
import com.json.exersice.repositories.UserRepository;
import com.json.exersice.services.ProductService;
import com.json.exersice.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ValidatorUtil validatorUtil, ModelMapper modelMapper, ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedProducts(ProductSeedDto[] productSeedDtos) {

        for (ProductSeedDto productSeedDto : productSeedDtos) {
            productSeedDto.setSeller(this.getRandomSeller());
            productSeedDto.setBuyer(this.getRandomBuyer());
            productSeedDto.setCategories(this.getRandomCategories());
            if (!validatorUtil.isValid(productSeedDto)) {
                this.validatorUtil.violations(productSeedDto)
                        .forEach(v -> System.out.println(v.getMessage()));

                continue;
            }

            Product product = this.modelMapper.map(productSeedDto, Product.class);

            this.productRepository.saveAndFlush(product);
        }
    }

    private User getRandomSeller() {
        Random random = new Random();

        int id = random.nextInt((int) this.userRepository.count() - 1) + 1;

        return this.userRepository.getOne(id);
    }

    private User getRandomBuyer() {
        Random random = new Random();

        int id = random.nextInt((int) this.userRepository.count() - 1) + 1;

        if (id % 4 == 0) {
            return null;
        }

        return this.userRepository.getOne(id);
    }

    private Category getRandomCategory() {
        Random random = new Random();

        int id = random.nextInt((int) this.categoryRepository.count() - 1) + 1;

        return this.categoryRepository.findById(id).orElse(null);
    }

    private Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();
        Random random = new Random();

        int size = random.nextInt((int) this.categoryRepository.count() - 1) + 1;

        for (int i = 0; i < size; i++) {

            categories.add(getRandomCategory());

        }

        return categories;
    }
}
