package com.xapi.controllers;


import com.xapi.config.ServiceConfig;
import com.xapi.services.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private ServiceConfig serviceConfig;

    @RequestMapping("/exchange/{currency}")
    public ResponseEntity getExchangeRateInfo(@PathVariable("currency") String currency) {
        return exchangeRateService.getExchangeRate(currency);
    }
}
