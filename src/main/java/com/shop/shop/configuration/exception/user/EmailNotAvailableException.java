package com.shop.shop.configuration.exception.user;

public class EmailNotAvailableException extends RuntimeException{
    public EmailNotAvailableException(String message) {
        super(message);
    }

    public EmailNotAvailableException(Throwable cause) {
        super(cause);
    }
}