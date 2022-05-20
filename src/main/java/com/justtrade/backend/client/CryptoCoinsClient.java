package com.justtrade.backend.client;

import com.justtrade.backend.dto.CoinResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class CryptoCoinsClient {

    @Value("${service.backend-2.host}")
    private String coinCryptoBaseUrl;

    @Value("${service.backend-2.dataCoin}")
    private String getDataCoinByCode;

    @Autowired
    @Qualifier("serviceRestTemplate")
    private RestTemplate restTemplate;

    @Cacheable("coin-price")
    public CoinResponseDto getDataCoin(String code) {
        CoinResponseDto coinResponseDto = null;
        String uri = coinCryptoBaseUrl + getDataCoinByCode.replace("{code}",code);
        try {
            coinResponseDto =
                    restTemplate.getForObject(uri,CoinResponseDto.class);

        } catch (HttpClientErrorException e) {
            throw new HttpClientErrorException(
                    HttpStatus.NOT_FOUND,
                    "Exception occurred while hitting the user service"
            );
        }
        return coinResponseDto;
    }
}
