package com.justtrade.backend.service;

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

    public void registerUser(RegistrationDto registrationDto){
        DataUser newDataUser = mapperFacade.map(registrationDto, DataUser.class);
        dataUserRepository.save(newDataUser);
    }
}
