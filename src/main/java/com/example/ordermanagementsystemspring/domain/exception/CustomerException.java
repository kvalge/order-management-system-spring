package com.example.ordermanagementsystemspring.domain.exception;

public class CustomerException extends AppException {

    public CustomerException(String message) {
        super(message);
    }

    public CustomerException(String message, int code) {
        super(message);
        setCode(code);
    }

    public CustomerException(Exception e) {
        super(e);
    }
}
