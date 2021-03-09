package com.spring.advancedqueringspring.repositories;

import com.spring.advancedqueringspring.entities.Ingredient;
import com.spring.advancedqueringspring.entities.Shampoo;
import com.spring.advancedqueringspring.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
//    List<Shampoo> findByBrand(String brand);
//
//    List<Shampoo> findBySizeOrderById(Size size);

//    List<Shampoo> findBySizeOrLabelIdOrderByPrice(Size size, Long labelId);

    //List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

   // List<Shampoo> findByPriceIsLessThan(BigDecimal price);



}
