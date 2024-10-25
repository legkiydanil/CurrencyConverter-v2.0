package com.demo.CurrencyConverter.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class ExchangeRateService {

    @Value("${exchangerate.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public ExchangeRateService() {
        this.restTemplate = new RestTemplate();
    }

    @Cacheable(value = "exchangeRates", key = "#from + '_' + #to", cacheManager = "cacheManager")
    public double getExchangeRate(String from, String to) {
        String url = apiUrl + "/" + from;

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        if (response == null || !response.containsKey("conversion_rates")) {
            throw new RuntimeException("Failed to get exchange rates");
        }

        Map<String, Double> rates = (Map<String, Double>) response.get("conversion_rates");

        Double rate = rates.get(to);
        if (rate == null) {
            throw new IllegalArgumentException("Conversion rate not available for " + from + " to " + to);
        }

        return rate;
    }
}

