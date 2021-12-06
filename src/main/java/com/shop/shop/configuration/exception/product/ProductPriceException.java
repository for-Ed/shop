package com.shop.shop.configuration.exception.product;

public class ProductPriceException extends  RuntimeException{
    public ProductPriceException(String message) {
        super(message);
    }

    public ProductPriceException(Throwable cause) {
        super(cause);
    }
}