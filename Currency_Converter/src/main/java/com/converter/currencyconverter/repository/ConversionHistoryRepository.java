package com.converter.currencyconverter.repository;

import com.converter.currencyconverter.entity.ConversionHistory;
import com.converter.currencyconverter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversionHistoryRepository extends JpaRepository<ConversionHistory, Long> {
    
    List<ConversionHistory> findByUserOrderByConversionDateDesc(User user);
    
    List<ConversionHistory> findByUserAndFromCurrencyAndToCurrencyOrderByConversionDateDesc(
            User user, String fromCurrency, String toCurrency);
}
