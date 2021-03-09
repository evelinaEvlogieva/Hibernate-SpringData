package com.json.exersice.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.json.exersice.util.impl.FileUtilImpl;
import com.json.exersice.util.impl.ValidatorUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public Gson gson(){
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public FileUtilImpl fileUtil(){
        return new FileUtilImpl();

    }

    public Validator validator(){
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Bean
    public ValidatorUtilImpl validatorUtil(){
        return new ValidatorUtilImpl(validator());
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
