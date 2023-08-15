package com.justtrade.backend.controller;

import com.justtrade.backend.dto.GetUserDataRequestDto;
import com.justtrade.backend.dto.RegistrationDto;
import com.justtrade.backend.entity.DataUser;
import com.justtrade.backend.service.UserService;
import com.justtrade.backend.validator.constraint.IdNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-service")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    //Api Registrasi User
    @PostMapping("/users/registration")
    public ResponseEntity<String> createAccount(
           @RequestBody @Validated RegistrationDto registrationDto){
        userService.registerUser(registrationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Api Soft Delete User
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deactiveUser(
            @IdNotFound @PathVariable("id") Long idUser){
        userService.softDeleteUser();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Api Upgrade User
    @PatchMapping("/users/{id}")
    public ResponseEntity<DataUser> upgradeUser(
            @IdNotFound @PathVariable("id") Long idUser){
        return new ResponseEntity<>(userService.upgradeUser(),HttpStatus.ACCEPTED);
    }

    //Api Get All User
    @GetMapping("/users")
    public Page<DataUser> getAllUser(GetUserDataRequestDto requestDto,Pageable pageable){
        return userService.getAllUser(requestDto,pageable);
    }

    //Api Get User by Id
    @GetMapping("/users/{id}")
    public DataUser getDataUserById(
            @IdNotFound @PathVariable("id") Long idUser){
        return userService.getDataUserById(idUser);
    }
}
