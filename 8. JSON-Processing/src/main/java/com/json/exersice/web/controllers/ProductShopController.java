package com.json.exersice.web.controllers;

import com.google.gson.Gson;
import com.json.exersice.domain.dtos.CategorySeedDto;
import com.json.exersice.domain.dtos.ProductSeedDto;
import com.json.exersice.domain.dtos.UserSeedDto;
import com.json.exersice.services.CategoryService;
import com.json.exersice.services.ProductService;
import com.json.exersice.services.UserService;
import com.json.exersice.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class ProductShopController implements CommandLineRunner {

    private final static String USER_JSON_FILE_PATH =
            "D:\\My Documents\\Java DB\\Hibernate\\8.JSON PROCESSING\\json exersice\\src\\main\\resources\\jsons\\users.json";


    private final static String CATEGORY_JSON_FILE_PATH =
            "D:\\My Documents\\Java DB\\Hibernate\\8.JSON PROCESSING\\json exersice\\src\\main\\resources\\jsons\\categories.json";


    private final static String PRODUCT_JSON_FILE_PATH =
            "D:\\My Documents\\Java DB\\Hibernate\\8.JSON PROCESSING\\json exersice\\src\\main\\resources\\jsons\\products.json";

    private final Gson gson;
    private final FileUtil fileUtil;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;

    @Autowired
    public ProductShopController(Gson gson, FileUtil fileUtil, UserService userService, CategoryService categoryService, ProductService productService) {
        this.gson = gson;
        this.fileUtil = fileUtil;
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedUsers();
        seedCategories();
        seedProducts();



    }


    private void seedUsers() throws IOException {
        String content = this.fileUtil.fileContent(USER_JSON_FILE_PATH);

        UserSeedDto[] userSeedDtos = this.gson.fromJson(content, UserSeedDto[].class);

        this.userService.seedUsers(userSeedDtos);
    }

    private void seedCategories() throws IOException {
        String content = this.fileUtil.fileContent(CATEGORY_JSON_FILE_PATH);

        CategorySeedDto[] categorySeedDtos = this.gson.fromJson(content, CategorySeedDto[].class);

        this.categoryService.seedCategories(categorySeedDtos);

    }

    private void seedProducts() throws IOException {
        String content = this.fileUtil.fileContent(PRODUCT_JSON_FILE_PATH);

        ProductSeedDto[] productSeedDtos = this.gson.fromJson(content, ProductSeedDto[].class);

        this.productService.seedProducts(productSeedDtos);

    }
}
