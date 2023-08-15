package com.justtrade.backend.service;

import com.justtrade.backend.dto.GetUserDataRequestDto;
import com.justtrade.backend.dto.LoginDto;
import com.justtrade.backend.dto.RegistrationDto;
import com.justtrade.backend.entity.DataUser;
import com.justtrade.backend.entity.UserHasMoney;
import com.justtrade.backend.repository.DataUserRepository;
import com.justtrade.backend.repository.UserHasMoneyRepository;
import com.justtrade.backend.service.jpaspecification.QueryCriteria;
import com.justtrade.backend.service.jpaspecification.QuerySpecification;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Objects;

@Service
@RequestScope
public class UserService {

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private DataUserRepository dataUserRepository;
    @Autowired
    private UserHasMoneyRepository userHasMoneyRepository;
    public DataUser dataUserByUsername;
    public DataUser dataUserByEmail;
    public DataUser dataUserById;
    public void registerUser(RegistrationDto registrationDto){
        DataUser newDataUser = mapperFacade.map(registrationDto, DataUser.class);
        newDataUser.setStatus(DataUser.Status.BASIC);
        dataUserRepository.save(newDataUser);
        userHasMoneyRepository.save(UserHasMoney.builder().user(newDataUser).build());
    }

    public void softDeleteUser(){
        this.dataUserById.setActive(false);
        dataUserRepository.save(this.dataUserById);
    }

    public DataUser upgradeUser(){
        this.dataUserById.setStatus(DataUser.Status.PREMIUM);
        return dataUserRepository.save(this.dataUserById);
    }

    public Page<DataUser> getAllUser(GetUserDataRequestDto requestDto,Pageable pageable){
        QuerySpecification<DataUser> filterUser = new QuerySpecification<>();
        filterUser.add(new QueryCriteria<>("isActive",requestDto.getIsActive(),
                QueryCriteria.CriteriaOperation.EQUAL_IGNORE_CASE));
        filterUser.add(new QueryCriteria<>("email",requestDto.getEmail(),
                QueryCriteria.CriteriaOperation.EQUAL_IGNORE_CASE));
        filterUser.add(new QueryCriteria<>("status",requestDto.getStatus(),
                QueryCriteria.CriteriaOperation.EQUAL_IGNORE_CASE));
        return dataUserRepository.findAll(filterUser,pageable);
    }

    //Api For Register User
    public DataUser loginUser(LoginDto loginDto){
        if(Objects.nonNull(this.dataUserByUsername)) {
            if (this.dataUserByUsername.getPassword().equalsIgnoreCase(loginDto.getPassword())) {
                return this.dataUserByUsername;
            }
        }else{
            if(this.dataUserByEmail.getPassword().equalsIgnoreCase(loginDto.getPassword())) {
                return this.dataUserByEmail;
            }
        }
        return null;
    }

    public DataUser getDataUserByUsername(String username){
        if(Objects.isNull(this.dataUserByUsername)){
            this.dataUserByUsername = dataUserRepository.findByUsernameAndIsActiveTrue(username).orElse(null);
        }
        return this.dataUserByUsername;
    }

    public DataUser getDataUserByEmail(String email){
        if(Objects.isNull(this.dataUserByEmail)){
            this.dataUserByEmail = dataUserRepository.findByEmail(email).orElse(null);
        }
        return this.dataUserByEmail;
    }

    public DataUser getDataUserById(Long id){
        if(Objects.isNull(this.dataUserById)){
            this.dataUserById = dataUserRepository.findById(id).orElse(null);
        }
        return this.dataUserById;
    }
}
