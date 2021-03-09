package com.spring.advancedqueringspring.repositories;

import com.spring.advancedqueringspring.entities.Ingredient;
import com.spring.advancedqueringspring.entities.Shampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

//    List<Ingredient> findByNameStartingWith(String letter);


}
