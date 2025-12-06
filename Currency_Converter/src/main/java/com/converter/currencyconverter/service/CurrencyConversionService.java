package com.converter.currencyconverter.service;

import com.converter.currencyconverter.dto.ConversionHistoryDTO;
import com.converter.currencyconverter.dto.ConversionRequest;
import com.converter.currencyconverter.dto.ConversionResponse;
import com.converter.currencyconverter.entity.ConversionHistory;
import com.converter.currencyconverter.entity.User;
import com.converter.currencyconverter.repository.ConversionHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyConversionService {
    
    private final ExchangeRateService exchangeRateService;
    private final ConversionHistoryRepository conversionHistoryRepository;
    
    /**
     * Convert currency and save to history
     */
    @Transactional
    public ConversionResponse convertCurrency(ConversionRequest request, User user) {
        log.info("Converting {} {} to {}", request.getAmount(), request.getFromCurrency(), request.getToCurrency());
        
        // Get exchange rate
        BigDecimal exchangeRate = exchangeRateService.getExchangeRate(
                request.getFromCurrency(), 
                request.getToCurrency()
        );
        
        // Calculate converted amount
        BigDecimal convertedAmount = request.getAmount()
                .multiply(exchangeRate)
                .setScale(4, RoundingMode.HALF_UP);
        
        // Save to history
        ConversionHistory history = new ConversionHistory();
        history.setUser(user);
        history.setFromCurrency(request.getFromCurrency().toUpperCase());
        history.setToCurrency(request.getToCurrency().toUpperCase());
        history.setAmount(request.getAmount());
        history.setConvertedAmount(convertedAmount);
        history.setExchangeRate(exchangeRate);
        
        conversionHistoryRepository.save(history);
        
        log.info("Conversion successful: {} {} = {} {}", 
                request.getAmount(), request.getFromCurrency(), 
                convertedAmount, request.getToCurrency());
        
        return new ConversionResponse(
                request.getFromCurrency().toUpperCase(),
                request.getToCurrency().toUpperCase(),
                request.getAmount(),
                convertedAmount,
                exchangeRate,
                LocalDateTime.now()
        );
    }
    
    /**
     * Get conversion history for a user
     */
    public List<ConversionHistoryDTO> getConversionHistory(User user) {
        log.debug("Fetching conversion history for user: {}", user.getUsername());
        
        List<ConversionHistory> history = conversionHistoryRepository
                .findByUserOrderByConversionDateDesc(user);
        
        return history.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Get conversion history filtered by currency pair
     */
    public List<ConversionHistoryDTO> getConversionHistoryByCurrencyPair(
            User user, String fromCurrency, String toCurrency) {
        log.debug("Fetching conversion history for user: {} from {} to {}", 
                user.getUsername(), fromCurrency, toCurrency);
        
        List<ConversionHistory> history = conversionHistoryRepository
                .findByUserAndFromCurrencyAndToCurrencyOrderByConversionDateDesc(
                        user, fromCurrency.toUpperCase(), toCurrency.toUpperCase());
        
        return history.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Convert entity to DTO
     */
    private ConversionHistoryDTO convertToDTO(ConversionHistory history) {
        return new ConversionHistoryDTO(
                history.getId(),
                history.getFromCurrency(),
                history.getToCurrency(),
                history.getAmount(),
                history.getConvertedAmount(),
                history.getExchangeRate(),
                history.getConversionDate()
        );
    }
}
