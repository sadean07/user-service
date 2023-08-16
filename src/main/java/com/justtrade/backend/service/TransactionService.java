package com.justtrade.backend.service;

import com.justtrade.backend.client.CryptoCoinsClient;
import com.justtrade.backend.dto.CoinResponseDto;
import com.justtrade.backend.dto.transaction.*;
import com.justtrade.backend.entity.UserHasCoin;
import com.justtrade.backend.entity.UserHasMoney;
import com.justtrade.backend.repository.UserHasCoinRepository;
import com.justtrade.backend.repository.UserHasMoneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequestScope
public class TransactionService {
    @Autowired
    private UserHasMoneyRepository userHasMoneyRepository;
    @Autowired
    private UserHasCoinRepository userHasCoinRepository;
    @Autowired
    private CryptoCoinsClient cryptoCoinsClient;

    UserHasMoney userHasMoneyByUserId;


    public UserHasMoney getUserHasMoneyByUserId(Long userId){
        if(Objects.isNull(this.userHasMoneyByUserId)){
            this.userHasMoneyByUserId = userHasMoneyRepository.getByUserId(userId);
        }
        return userHasMoneyByUserId;
    }

    public BuyCoinResponseDto userPurchaseCoinTransaction(Long userId,BuyCoinRequestDto buyCoinRequestDto){
        CoinResponseDto coinResponseDto = cryptoCoinsClient.getDataCoin(buyCoinRequestDto.getCoinCode()); //get coin data from coin service
        UserHasMoney userMoney = this.getUserHasMoneyByUserId(userId); //get user data

        //Update user amount data
        int usedAmount = buyCoinRequestDto.getNumberOfBuy()*coinResponseDto.getHarga();
        BigDecimal remainAmount = userMoney.getAmount().subtract(new BigDecimal(usedAmount));
        userMoney.setAmount(remainAmount);
        userHasMoneyRepository.save(userMoney);

        //Update user has coin data
        UserHasCoin userHasCoin;
        userHasCoin = userHasCoinRepository.getByDataUserIdAndCoinId(userId, coinResponseDto.getId());
        if(Objects.nonNull(userHasCoin)){
            userHasCoin.setJumlah(userHasCoin.getJumlah()+ buyCoinRequestDto.getNumberOfBuy());
            userHasCoinRepository.save(userHasCoin);
        }
        else {
            userHasCoin = UserHasCoin.builder()
                    .dataUser(userMoney.getUser())
                    .jumlah(buyCoinRequestDto.getNumberOfBuy())
                    .coinId(coinResponseDto.getId())
                    .build();
            userHasCoinRepository.save(userHasCoin);
        }

        return BuyCoinResponseDto.builder()
                .coinData(coinResponseDto)
                .remainAmount(remainAmount)
                .usedAmount(new BigDecimal(usedAmount))
                .numberOfBuy(buyCoinRequestDto.getNumberOfBuy())
                .currentCoinData(userHasCoin.getJumlah())
                .build();
    }

    public SellCoinResponseDto userSellCoin(Long userId, SellCoinRequestDto sellCoinRequestDto){
        CoinResponseDto coinResponseDto = cryptoCoinsClient.getDataCoin(sellCoinRequestDto.getCoinCode());//get coin data from coin service
        UserHasMoney userMoney = this.getUserHasMoneyByUserId(userId); //get user data

        //Update user amount data
        BigDecimal gotAmount = new BigDecimal(sellCoinRequestDto.getNumberOfSell()*coinResponseDto.getHarga());
        BigDecimal totalAmount = userMoney.getAmount().add(gotAmount);
        BigDecimal currentAmount = userMoney.getAmount();
        userMoney.setAmount(totalAmount);
        userHasMoneyRepository.save(userMoney);

        //Update user has coin data
        UserHasCoin userHasCoin = userHasCoinRepository.getByDataUserIdAndCoinId(userId, coinResponseDto.getId());
        userHasCoin.setJumlah(userHasCoin.getJumlah() - sellCoinRequestDto.getNumberOfSell());
        userHasCoin = userHasCoinRepository.save(userHasCoin);

        return SellCoinResponseDto.builder()
                .coinData(coinResponseDto)
                .currentAmount(currentAmount)
                .gotAmount(gotAmount)
                .totalAmount(userMoney.getAmount())
                .numberOfSell(sellCoinRequestDto.getNumberOfSell())
                .remainCoinData(userHasCoin.getJumlah())
                .build();
    }

    public SellAllCoinResponseDto userSellAllCoin(Long userId){
        List<CoinResponseDto> listCoinData = new ArrayList<>();
        final BigDecimal[] tempTotalAmount = {BigDecimal.ZERO};

        //Update user has coin data
        userHasCoinRepository.findAll().stream().forEach(
                data -> {
                    CoinResponseDto coinResponseDto = cryptoCoinsClient.getDataCoin(data.getCoinId().toString());
                    listCoinData.add(coinResponseDto);
                    UserHasCoin userHasCoin = userHasCoinRepository.getByDataUserIdAndCoinId(userId, coinResponseDto.getId());
                    tempTotalAmount[0] = tempTotalAmount[0].add(new BigDecimal(userHasCoin.getJumlah()*coinResponseDto.getHarga()));
                    userHasCoin.setJumlah(0);
                    userHasCoinRepository.save(userHasCoin);
                }
        );
        UserHasMoney userMoney = this.getUserHasMoneyByUserId(userId); //get user data

        //Update user amount data
        BigDecimal currentAmount = userMoney.getAmount();
        BigDecimal totalAmount = userMoney.getAmount().add(tempTotalAmount[0]);
        userMoney.setAmount(totalAmount);
        userHasMoneyRepository.save(userMoney);

        return SellAllCoinResponseDto.builder()
                .coinData(listCoinData)
                .currentAmount(currentAmount)
                .gotAmount(tempTotalAmount[0])
                .totalAmount(userMoney.getAmount())
                .build();
    }
}
