package com.justtrade.backend.controller;

import com.justtrade.backend.dto.transaction.*;
import com.justtrade.backend.service.TransactionService;
import com.justtrade.backend.validator.constraint.IdNotFound;
import com.justtrade.backend.validator.constraint.RemainAmountValid;
import com.justtrade.backend.validator.constraint.UserHasCoinValid;
import com.justtrade.backend.validator.groups.ValidateFirst;
import com.justtrade.backend.validator.groups.ValidateSecond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.GroupSequence;

@RestController
@RequestMapping("/transaction-service")
@Validated
@GroupSequence(value = {TransactionController.class,ValidateFirst.class, ValidateSecond.class})
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/users/{id}/purchase")
    @RemainAmountValid(groups = ValidateSecond.class)
    public BuyCoinResponseDto userPurchaseCoin(
            @PathVariable("id") @Validated @IdNotFound(groups = ValidateFirst.class) Long userId,
            @RequestBody @Validated BuyCoinRequestDto buyCoinRequestDto){
        return transactionService.userPurchaseCoinTransaction(userId,buyCoinRequestDto);
    }

    @PostMapping("/users/{id}/exchange")
    @UserHasCoinValid(groups = ValidateSecond.class)
    public SellCoinResponseDto userSellACoin(
            @PathVariable("id") @Validated @IdNotFound(groups = ValidateFirst.class) Long userId,
            @RequestBody @Validated SellCoinRequestDto sellCoinRequestDto){
        return transactionService.userSellCoin(userId,sellCoinRequestDto);
    }

    @PostMapping("/users/{id}/exchanges")
    public SellAllCoinResponseDto userSellAllCoin(
            @PathVariable("id") @Validated @IdNotFound(groups = ValidateFirst.class) Long userId){
        return transactionService.userSellAllCoin(userId);
    }
}
