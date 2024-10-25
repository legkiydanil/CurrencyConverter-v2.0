package com.demo.CurrencyConverter.controllers;

import com.demo.CurrencyConverter.services.CurrencyConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/converter")
public class CurrencyConverterController {

    private final CurrencyConverterService converterService;

    @Autowired
    public CurrencyConverterController(CurrencyConverterService converterService) {
        this.converterService = converterService;
    }

    @GetMapping
    public double convert(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam double amount) {
        return converterService.convert(from, to, amount);
    }
}
