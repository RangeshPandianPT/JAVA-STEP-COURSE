package com.converter.currencyconverter.controller;

import com.converter.currencyconverter.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/rates")
@RequiredArgsConstructor
public class ExchangeRateController {
    
    private final ExchangeRateService exchangeRateService;
    
    /**
     * Get all exchange rates for a base currency
     */
    @GetMapping("/{baseCurrency}")
    public ResponseEntity<Map<String, BigDecimal>> getAllRates(
            @PathVariable String baseCurrency) {
        Map<String, BigDecimal> rates = exchangeRateService.getAllRates(baseCurrency);
        return ResponseEntity.ok(rates);
    }
    
    /**
     * Get exchange rate between two currencies
     */
    @GetMapping("/{fromCurrency}/{toCurrency}")
    public ResponseEntity<Map<String, Object>> getExchangeRate(
            @PathVariable String fromCurrency,
            @PathVariable String toCurrency) {
        BigDecimal rate = exchangeRateService.getExchangeRate(fromCurrency, toCurrency);
        
        Map<String, Object> response = Map.of(
                "from", fromCurrency.toUpperCase(),
                "to", toCurrency.toUpperCase(),
                "rate", rate
        );
        
        return ResponseEntity.ok(response);
    }
}
