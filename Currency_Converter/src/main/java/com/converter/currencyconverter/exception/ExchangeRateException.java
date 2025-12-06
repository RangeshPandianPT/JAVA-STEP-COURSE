package com.converter.currencyconverter.exception;

public class ExchangeRateException extends RuntimeException {
    
    public ExchangeRateException(String message) {
        super(message);
    }
    
    public ExchangeRateException(String message, Throwable cause) {
        super(message, cause);
    }
}
