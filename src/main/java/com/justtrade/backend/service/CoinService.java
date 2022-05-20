package com.justtrade.backend.service;

import com.justtrade.backend.client.CryptoCoinsClient;
import com.justtrade.backend.dto.CoinResponseDto;
import com.justtrade.backend.dto.DataCoinRequestDto;
import com.justtrade.backend.entity.DataCoin;
import com.justtrade.backend.repository.DataCoinRepository;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoinService {

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private DataCoinRepository dataCoinRepository;

    @Autowired
    CryptoCoinsClient cryptoCoinsClient;

    public void saveDataCoin(DataCoinRequestDto dataCoinRequestDto){
        CoinResponseDto coinResponseDto = cryptoCoinsClient.getDataCoin(dataCoinRequestDto.getCode());
        DataCoin dataCoin = mapperFacade.map(coinResponseDto,DataCoin.class);
        dataCoinRepository.save(dataCoin);
    }
}
