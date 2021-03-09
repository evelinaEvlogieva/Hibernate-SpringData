package com.example.mappingobjects.services;

import com.example.mappingobjects.domain.dtos.UserLoginDto;
import com.example.mappingobjects.domain.dtos.UserRegisterDto;

public interface UserService {

    String registerUser(UserRegisterDto userRegisterDto);

    String loginUser(UserLoginDto userLoginDto);

    String logoutUser();
}
