package com.example.ordermanagementsystemspring.domain.exception;

public class OrderException extends AppException {

    public OrderException(String message) {
        super(message);
    }

    public OrderException(String message, int code) {
        super(message);
        setCode(code);
    }

    public OrderException(Exception e) {
        super(e);
    }
}
