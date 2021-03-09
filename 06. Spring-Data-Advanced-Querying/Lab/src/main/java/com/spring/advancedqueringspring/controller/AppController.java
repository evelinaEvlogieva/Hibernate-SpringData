package com.spring.advancedqueringspring.controller;

import com.spring.advancedqueringspring.entities.Ingredient;
import com.spring.advancedqueringspring.entities.Shampoo;
import com.spring.advancedqueringspring.repositories.IngredientRepository;
import com.spring.advancedqueringspring.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.*;

@Controller

public class AppController implements CommandLineRunner {
    private final ShampooRepository shampooRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public AppController(ShampooRepository shampooRepository, IngredientRepository ingredientRepository) {
        this.shampooRepository = shampooRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();


//        this.shampooRepository
//                .findByBrand(input)
//                .forEach(p -> System.out.printf("%d %s %.2f%n",
//                        p.getId(),
//                        p.getBrand(),
//                        p.getPrice()));


//        this.shampooRepository
//                .findBySizeOrderById(Size.valueOf(input))
//                .forEach(p -> System.out.printf("%s %s %.2f%n",
//                        p.getBrand(),
//                        p.getSize(),
//                        p.getPrice()));

//        Long labelId = Long.parseLong(scanner.nextLine());
//        this.shampooRepository
//                .findBySizeOrLabelIdOrderByPrice(Size.valueOf(input), labelId)
//                .forEach(p -> System.out.printf("%s %s %.2f%n",
//                        p.getBrand(),
//                        p.getSize(),
//                        p.getPrice()));

//        BigDecimal price = new BigDecimal(input);
//        this.shampooRepository
//                .findByPriceGreaterThanOrderByPriceDesc(price)
//                .forEach(p -> System.out.printf("%s %s %.2f%n",
//                        p.getBrand(),
//                        p.getSize(),
//                        p.getPrice()));


//        this.ingredientRepository
//                .findByNameStartingWith(input)
//                .forEach(p -> System.out.println(p.getName()));

//        BigDecimal price = new BigDecimal(input);
//        List<Shampoo> shampoos = this.shampooRepository
//                .findByPriceIsLessThan(price);
//
//        System.out.println(shampoos.size());


    }
}
