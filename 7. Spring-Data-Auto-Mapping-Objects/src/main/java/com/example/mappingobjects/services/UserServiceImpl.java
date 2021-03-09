package com.example.mappingobjects.services;

import com.example.mappingobjects.domain.dtos.UserLoginDto;
import com.example.mappingobjects.domain.dtos.UserRegisterDto;
import com.example.mappingobjects.domain.entities.Role;
import com.example.mappingobjects.domain.entities.User;
import com.example.mappingobjects.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private ModelMapper modelMapper;
    private String loggedInInUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
        this.loggedInInUser = "";
    }

    @Override
    public String registerUser(UserRegisterDto userRegisterDto) {
        StringBuilder sb = new StringBuilder();

        User user = this.modelMapper.map(userRegisterDto, User.class);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        User inDb = this.userRepository.findByEmail(user.getEmail()).orElse(null);

        if (inDb != null) {
            return sb.append("User is already registered").toString();
        }


        if (violations.size() > 0) {
            for (ConstraintViolation<User> violation : violations) {
                sb.append(violation.getMessage()).append(System.lineSeparator());
            }
        } else {
            if (this.userRepository.count() == 0) {
                user.setRole(Role.ADMIN);
            } else {
                user.setRole(Role.USER);
            }

            sb.append(String.format("%s id registered", user.getFullName()));
            this.userRepository.saveAndFlush(user);
        }
        return sb.toString();
    }

    @Override
    public String loginUser(UserLoginDto userLoginDto) {
        StringBuilder sb = new StringBuilder();

        if (!this.loggedInInUser.equals("")){
           return sb.append("User is already logged in").toString();
        }

        User user = this.userRepository.findByEmail(userLoginDto.getEmail()).orElse(null);


        if (user == null) {
            return sb.append("Incorrect email").toString();
        } else {
            if (!user.getPassword().equals(userLoginDto.getPassword())){
                return sb.append("Incorrect password").toString();
            }

            this.loggedInInUser = user.getEmail();
            sb.append(String.format("Successfully logged in %s", user.getFullName()));

        }
        return sb.toString();
    }

    @Override
    public String logoutUser() {
        StringBuilder sb = new StringBuilder();

        if (this.loggedInInUser.isEmpty()){
            sb.append("Cannot log out. No user was logged in.");
        } else {
            User user = this.userRepository.findByEmail(this.loggedInInUser).orElse(null);
            sb.append(String.format("User %s is successfully log out.", user.getFullName()));
            this.loggedInInUser = "";
        }
        return sb.toString();
    }
}
