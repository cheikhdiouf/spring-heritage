package com.example.backendspring.exception;

public class BankAccountNotFoundException extends Exception{
    public BankAccountNotFoundException(String message) {
        super(message);
    }
}
