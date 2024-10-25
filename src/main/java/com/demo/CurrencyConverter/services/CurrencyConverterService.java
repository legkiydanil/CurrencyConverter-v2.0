package com.demo.CurrencyConverter.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConverterService {

    private final ExchangeRateService exchangeRateService;

    @Autowired
    public CurrencyConverterService(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    public double convert(String from, String to, double amount) {
        double rate = exchangeRateService.getExchangeRate(from, to);
        if (rate == 0.0) {
            throw new IllegalArgumentException("Conversion rate not available for " + from + " to " + to);
        }
        return amount * rate;
    }
}

