package com.converter.currencyconverter.controller;

import com.converter.currencyconverter.dto.UserRegistrationRequest;
import com.converter.currencyconverter.dto.UserRegistrationResponse;
import com.converter.currencyconverter.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final UserService userService;
    
    /**
     * Register a new user
     */
    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponse> register(
            @Valid @RequestBody UserRegistrationRequest request) {
        UserRegistrationResponse response = userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    /**
     * Regenerate API key (requires authentication)
     */
    @PostMapping("/regenerate-api-key")
    public ResponseEntity<Map<String, String>> regenerateApiKey(Principal principal) {
        String newApiKey = userService.regenerateApiKey(principal.getName());
        Map<String, String> response = new HashMap<>();
        response.put("apiKey", newApiKey);
        response.put("message", "API key regenerated successfully");
        return ResponseEntity.ok(response);
    }
    
    /**
     * Health check endpoint (public)
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("message", "Currency Converter API is running");
        return ResponseEntity.ok(response);
    }
}
