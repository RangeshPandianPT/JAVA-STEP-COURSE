package com.converter.currencyconverter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "conversion_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversionHistory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(nullable = false, length = 3)
    private String fromCurrency;
    
    @Column(nullable = false, length = 3)
    private String toCurrency;
    
    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;
    
    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal convertedAmount;
    
    @Column(nullable = false, precision = 19, scale = 6)
    private BigDecimal exchangeRate;
    
    @Column(nullable = false)
    private LocalDateTime conversionDate;
    
    @PrePersist
    protected void onCreate() {
        conversionDate = LocalDateTime.now();
    }
}
