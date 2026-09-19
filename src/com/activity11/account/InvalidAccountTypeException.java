package com.activity11.account;

public class InvalidAccountTypeException extends IllegalArgumentException {
    public InvalidAccountTypeException(String message) {
        super(message);
    }
}
