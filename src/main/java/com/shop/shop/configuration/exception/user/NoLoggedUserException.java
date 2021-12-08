package com.shop.shop.configuration.exception.user;

public class NoLoggedUserException extends RuntimeException{
    public NoLoggedUserException(String message) {
        super(message);
    }

    public NoLoggedUserException(Throwable cause) {
        super(cause);
    }
}