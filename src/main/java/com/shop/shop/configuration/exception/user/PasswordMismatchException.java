package com.shop.shop.configuration.exception.user;

public class PasswordMismatchException extends RuntimeException{
    public PasswordMismatchException(String message) {
        super(message);
    }

    public PasswordMismatchException(Throwable cause) {
        super(cause);
    }
}