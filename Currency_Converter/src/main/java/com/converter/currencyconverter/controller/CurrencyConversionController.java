package com.converter.currencyconverter.controller;

import com.converter.currencyconverter.dto.ConversionHistoryDTO;
import com.converter.currencyconverter.dto.ConversionRequest;
import com.converter.currencyconverter.dto.ConversionResponse;
import com.converter.currencyconverter.entity.User;
import com.converter.currencyconverter.service.CurrencyConversionService;
import com.converter.currencyconverter.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/convert")
@RequiredArgsConstructor
public class CurrencyConversionController {
    
    private final CurrencyConversionService conversionService;
    private final UserService userService;
    
    /**
     * Convert currency
     */
    @PostMapping
    public ResponseEntity<ConversionResponse> convertCurrency(
            @Valid @RequestBody ConversionRequest request,
            Principal principal) {
        User user = userService.findByUsername(principal.getName());
        ConversionResponse response = conversionService.convertCurrency(request, user);
        return ResponseEntity.ok(response);
    }
    
    /**
     * Get conversion history for authenticated user
     */
    @GetMapping("/history")
    public ResponseEntity<List<ConversionHistoryDTO>> getHistory(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<ConversionHistoryDTO> history = conversionService.getConversionHistory(user);
        return ResponseEntity.ok(history);
    }
    
    /**
     * Get conversion history filtered by currency pair
     */
    @GetMapping("/history/{fromCurrency}/{toCurrency}")
    public ResponseEntity<List<ConversionHistoryDTO>> getHistoryByCurrencyPair(
            @PathVariable String fromCurrency,
            @PathVariable String toCurrency,
            Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<ConversionHistoryDTO> history = conversionService.getConversionHistoryByCurrencyPair(
                user, fromCurrency, toCurrency);
        return ResponseEntity.ok(history);
    }
}
