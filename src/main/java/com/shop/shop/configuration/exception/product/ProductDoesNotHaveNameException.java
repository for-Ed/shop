package com.shop.shop.configuration.exception.product;

public class ProductDoesNotHaveNameException extends RuntimeException {
    public ProductDoesNotHaveNameException(String message) {
        super(message);
    }
    public ProductDoesNotHaveNameException(String message, Throwable cause) {
        super(message, cause);
    }
}