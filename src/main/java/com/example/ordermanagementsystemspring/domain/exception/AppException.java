package com.example.ordermanagementsystemspring.domain.exception;

import lombok.Data;

@Data
public class AppException extends RuntimeException{

    private int code;

    public AppException(String message){
        super(message);
    }

    public AppException(Exception e){
        super(e);
    }
}
