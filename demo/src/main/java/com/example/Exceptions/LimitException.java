package com.example.Exceptions;

public class LimitException extends RuntimeException {
    
    public LimitException(String message){
        super(message);
    }
}
