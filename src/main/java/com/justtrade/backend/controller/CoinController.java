package com.justtrade.backend.controller;

import com.justtrade.backend.dto.CoinResponseDto;
import com.justtrade.backend.dto.DataCoinRequestDto;
import com.justtrade.backend.dto.RegistrationDto;
import com.justtrade.backend.service.CoinService;
import com.justtrade.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CoinController {
    @Autowired
    private CoinService coinService;

    @PostMapping("/coins")
    public ResponseEntity<String> createCoinData(
            @RequestBody DataCoinRequestDto dataCoinRequestDto){
        coinService.saveDataCoin(dataCoinRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
