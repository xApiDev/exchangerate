package com.xapi.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xapi.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExchangeRateService {

    @Autowired
    ServiceConfig exchangeRateConfig;

    private final RestTemplate restTemplate;

    public ExchangeRateService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @HystrixCommand(fallbackMethod="retrieveFallbackExchangeRate")
    public ResponseEntity getExchangeRate(String currency) {
        String apiProtocol = exchangeRateConfig.getApiProtocol();
        String apiExchangeRateDomain = exchangeRateConfig.getApiDomain();
        String apiExchangeRatePath = exchangeRateConfig.getApiPath();
        String urlApiExchangeRate = apiProtocol + "://" + apiExchangeRateDomain + apiExchangeRatePath + "?basse={currency}";
        ResponseEntity<String> response = restTemplate.getForEntity(urlApiExchangeRate , String.class, currency);
        return response;
    }

    @HystrixCommand
    public ResponseEntity retrieveFallbackExchangeRate(String currency) {
        ResponseEntity<String> response = restTemplate.getForEntity(new String(), String.class);
        return response;
    }
}
