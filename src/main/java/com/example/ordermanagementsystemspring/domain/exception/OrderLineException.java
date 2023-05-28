package com.example.ordermanagementsystemspring.domain.exception;

public class OrderLineException extends AppException {

    public OrderLineException(String message) {
        super(message);
    }

    public OrderLineException(String message, int code) {
        super(message);
        setCode(code);
    }

    public OrderLineException(Exception e) {
        super(e);
    }
}
