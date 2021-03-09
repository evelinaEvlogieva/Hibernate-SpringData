package com.json.exersice.domain.dtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Size;
import java.io.Serializable;

public class CategorySeedDto implements Serializable {

    @Expose
    private String name;

    public CategorySeedDto() {
    }

    @Size(min = 3, max = 15, message = "Category name should be between 3 and 15 symbols")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
