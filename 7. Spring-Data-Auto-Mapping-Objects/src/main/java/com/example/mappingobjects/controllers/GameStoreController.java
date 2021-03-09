package com.example.mappingobjects.controllers;

import com.example.mappingobjects.domain.dtos.UserLoginDto;
import com.example.mappingobjects.domain.dtos.UserRegisterDto;
import com.example.mappingobjects.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class GameStoreController implements CommandLineRunner {

    private final UserService userService;

    public GameStoreController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String[] params = scanner.nextLine().split("\\|");

            switch (params[0]) {
                case "RegisterUser":
                    UserRegisterDto userRegisterDto =
                            new UserRegisterDto(params[1], params[2], params[3], params[4]);
                    System.out.println(this.userService.registerUser(userRegisterDto));
                    break;

                case "LoginUser":
                    UserLoginDto userLoginDto =
                            new UserLoginDto(params[1], params[2]);

                    System.out.println(this.userService.loginUser(userLoginDto));
                    break;

                case "Logout":
                    System.out.println(this.userService.logoutUser());
                    break;

            }
        }
    }
}