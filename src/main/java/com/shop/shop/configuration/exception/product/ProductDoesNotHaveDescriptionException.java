package com.shop.shop.configuration.exception.product;

public class ProductDoesNotHaveDescriptionException extends RuntimeException {
    public ProductDoesNotHaveDescriptionException(String message) {
        super(message);
    }

    public ProductDoesNotHaveDescriptionException(Throwable cause) {
        super(cause);
    }
}