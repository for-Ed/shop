package com.shop.shop.configuration.exception.user;

public class ConfirmPasswordMismatchException extends RuntimeException{
    public ConfirmPasswordMismatchException(String message) {
        super(message);
    }

    public ConfirmPasswordMismatchException(Throwable cause) {
        super(cause);
    }
}