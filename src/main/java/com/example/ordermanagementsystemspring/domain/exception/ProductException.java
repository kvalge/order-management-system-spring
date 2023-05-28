package com.example.ordermanagementsystemspring.domain.exception;

public class ProductException extends AppException {
    public ProductException(String message) {
        super(message);
    }

    public ProductException(String message, int code) {
        super(message);
        setCode(code);
    }

    public ProductException(Exception e) {
        super(e);
    }
}
