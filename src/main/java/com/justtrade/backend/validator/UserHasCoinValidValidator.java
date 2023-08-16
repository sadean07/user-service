package com.justtrade.backend.validator;

import com.justtrade.backend.client.CryptoCoinsClient;
import com.justtrade.backend.dto.CoinResponseDto;
import com.justtrade.backend.dto.transaction.SellCoinRequestDto;
import com.justtrade.backend.entity.UserHasCoin;
import com.justtrade.backend.repository.UserHasCoinRepository;
import com.justtrade.backend.validator.constraint.UserHasCoinValid;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.util.Objects;


@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class UserHasCoinValidValidator implements ConstraintValidator<UserHasCoinValid, Object[]> {

    @Autowired
    private CryptoCoinsClient cryptoCoinsClient;
    @Autowired
    private UserHasCoinRepository userHasCoinRepository;

    @Override
    public boolean isValid(Object[] requestObjects, ConstraintValidatorContext constraintValidatorContext) {
        var userId = (Long) requestObjects[0];
        var requestDto = (SellCoinRequestDto) requestObjects[1];
        CoinResponseDto coinResponseDto = cryptoCoinsClient.getDataCoin(requestDto.getCoinCode());
        UserHasCoin userHasCoin = userHasCoinRepository.getByDataUserIdAndCoinId(userId,coinResponseDto.getId());
        if(Objects.nonNull(userHasCoin)){
            return (userHasCoin.getJumlah() - requestDto.getNumberOfSell()) >= 0;
        }
        return false;
    }
}
