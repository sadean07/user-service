package com.justtrade.backend.controller;

import com.justtrade.backend.dto.LoginDto;
import com.justtrade.backend.dto.RegistrationDto;
import com.justtrade.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    //API Registrasi User
    @PostMapping("/users/registration")
    public ResponseEntity<String> createAccount(
           @RequestBody RegistrationDto registrationDto){
        userService.registerUser(registrationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Api login
    @PostMapping("/users/login")
    public ResponseEntity<String> Login(
            @RequestBody LoginDto loginDto){
        String status = userService.loginUser(loginDto);
        return new ResponseEntity<>(status,HttpStatus.OK);
    }
}
