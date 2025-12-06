package com.converter.currencyconverter.service;

import com.converter.currencyconverter.dto.UserRegistrationRequest;
import com.converter.currencyconverter.dto.UserRegistrationResponse;
import com.converter.currencyconverter.entity.User;
import com.converter.currencyconverter.exception.UserAlreadyExistsException;
import com.converter.currencyconverter.exception.UserNotFoundException;
import com.converter.currencyconverter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    /**
     * Register a new user
     */
    @Transactional
    public UserRegistrationResponse registerUser(UserRegistrationRequest request) {
        log.info("Registering new user: {}", request.getUsername());
        
        // Check if username already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists: " + request.getUsername());
        }
        
        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists: " + request.getEmail());
        }
        
        // Generate API key
        String apiKey = generateApiKey();
        
        // Create user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setApiKey(apiKey);
        user.setEnabled(true);
        
        userRepository.save(user);
        
        log.info("User registered successfully: {}", user.getUsername());
        
        return new UserRegistrationResponse(
                user.getUsername(),
                user.getEmail(),
                apiKey,
                "User registered successfully. Please save your API key."
        );
    }
    
    /**
     * Find user by username
     */
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + username));
    }
    
    /**
     * Find user by API key
     */
    public User findByApiKey(String apiKey) {
        return userRepository.findByApiKey(apiKey)
                .orElseThrow(() -> new UserNotFoundException("Invalid API key"));
    }
    
    /**
     * Generate a unique API key
     */
    private String generateApiKey() {
        return "CK-" + UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
    
    /**
     * Regenerate API key for a user
     */
    @Transactional
    public String regenerateApiKey(String username) {
        User user = findByUsername(username);
        String newApiKey = generateApiKey();
        user.setApiKey(newApiKey);
        userRepository.save(user);
        
        log.info("API key regenerated for user: {}", username);
        return newApiKey;
    }
}
