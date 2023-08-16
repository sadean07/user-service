package com.justtrade.backend.validator;

import com.justtrade.backend.client.CryptoCoinsClient;
import com.justtrade.backend.dto.CoinResponseDto;
import com.justtrade.backend.dto.transaction.BuyCoinRequestDto;
import com.justtrade.backend.entity.UserHasMoney;
import com.justtrade.backend.service.TransactionService;
import com.justtrade.backend.validator.constraint.RemainAmountValid;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.math.BigDecimal;


@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class RemainAmountValidValidator implements ConstraintValidator<RemainAmountValid, Object[]> {

    @Autowired
    private CryptoCoinsClient cryptoCoinsClient;
    @Autowired
    private TransactionService transactionService;

    @Override
    public boolean isValid(Object[] requestObjects, ConstraintValidatorContext constraintValidatorContext) {
        var userId = (Long) requestObjects[0];
        var requestDto = (BuyCoinRequestDto) requestObjects[1];
        CoinResponseDto coinResponseDto = cryptoCoinsClient.getDataCoin(requestDto.getCoinCode());
        UserHasMoney userMoney = transactionService.getUserHasMoneyByUserId(userId);
        int usedAmount = requestDto.getNumberOfBuy()*coinResponseDto.getHarga();
        BigDecimal remainAmount = userMoney.getAmount().subtract(new BigDecimal(usedAmount));
        return remainAmount.compareTo(BigDecimal.ZERO) > 0;
    }
}
