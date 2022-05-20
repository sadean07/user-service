package com.justtrade.backend.service;

import com.justtrade.backend.dto.LoginDto;
import com.justtrade.backend.dto.RegistrationDto;
import com.justtrade.backend.entity.DataUser;
import com.justtrade.backend.repository.DataUserRepository;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private DataUserRepository dataUserRepository;

    //Api For Register User
    public void registerUser(RegistrationDto registrationDto){
        DataUser newDataUser = mapperFacade.map(registrationDto, DataUser.class);
        dataUserRepository.save(newDataUser);
    }

    //Api For Register User
    public String loginUser(LoginDto loginDto){
        DataUser dataUser = dataUserRepository.findByUsername(loginDto.getUsername()).orElseGet(null);
        if(dataUser.getPassword().equalsIgnoreCase(loginDto.getPassword())){
            return "Success";
        }
        return "Failed";
    }
}
