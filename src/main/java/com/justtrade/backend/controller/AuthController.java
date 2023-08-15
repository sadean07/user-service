package com.justtrade.backend.controller;

import com.justtrade.backend.dto.LoginDto;
import com.justtrade.backend.entity.DataUser;
import com.justtrade.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Validated
public class AuthController {
    @Autowired
    private UserService userService;
    //Api login
    @PostMapping("/users/login")
    public ResponseEntity<DataUser> Login(
            @RequestBody @Validated LoginDto loginDto){
        DataUser responseData = userService.loginUser(loginDto);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
