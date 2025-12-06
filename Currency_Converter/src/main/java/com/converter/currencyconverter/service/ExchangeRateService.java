package com.converter.currencyconverter.service;

import com.converter.currencyconverter.dto.ExchangeRateResponse;
import com.converter.currencyconverter.exception.ExchangeRateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Map;

@Service
@Slf4j
public class ExchangeRateService {
    
    private final WebClient webClient;
    
    @Value("${exchange.api.url}")
    private String apiUrl;
    
    @Value("${exchange.api.timeout}")
    private int timeout;
    
    public ExchangeRateService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }
    
    /**
     * Fetch exchange rates for a given base currency
     */
    public ExchangeRateResponse getExchangeRates(String baseCurrency) {
        try {
            log.debug("Fetching exchange rates for base currency: {}", baseCurrency);
            
            ExchangeRateResponse response = webClient.get()
                    .uri(apiUrl + "/" + baseCurrency.toUpperCase())
                    .retrieve()
                    .bodyToMono(ExchangeRateResponse.class)
                    .timeout(Duration.ofMillis(timeout))
                    .onErrorResume(e -> {
                        log.error("Error fetching exchange rates: {}", e.getMessage());
                        return Mono.error(new ExchangeRateException("Failed to fetch exchange rates: " + e.getMessage()));
                    })
                    .block();
            
            if (response == null || response.getRates() == null) {
                throw new ExchangeRateException("No exchange rates found for currency: " + baseCurrency);
            }
            
            log.debug("Successfully fetched {} exchange rates", response.getRates().size());
            return response;
            
        } catch (Exception e) {
            log.error("Error in getExchangeRates: {}", e.getMessage());
            throw new ExchangeRateException("Failed to fetch exchange rates: " + e.getMessage());
        }
    }
    
    /**
     * Get exchange rate between two currencies
     */
    public BigDecimal getExchangeRate(String fromCurrency, String toCurrency) {
        try {
            ExchangeRateResponse rates = getExchangeRates(fromCurrency);
            
            BigDecimal rate = rates.getRates().get(toCurrency.toUpperCase());
            if (rate == null) {
                throw new ExchangeRateException("Exchange rate not found for: " + toCurrency);
            }
            
            return rate;
            
        } catch (Exception e) {
            log.error("Error getting exchange rate from {} to {}: {}", fromCurrency, toCurrency, e.getMessage());
            throw new ExchangeRateException("Failed to get exchange rate: " + e.getMessage());
        }
    }
    
    /**
     * Get all supported currencies
     */
    public Map<String, BigDecimal> getAllRates(String baseCurrency) {
        ExchangeRateResponse response = getExchangeRates(baseCurrency);
        return response.getRates();
    }
}
