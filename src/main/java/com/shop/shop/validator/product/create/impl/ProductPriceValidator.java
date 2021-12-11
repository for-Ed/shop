package com.shop.shop.validator.product.create.impl;

import com.shop.shop.configuration.exception.product.ProductPriceException;
import com.shop.shop.entity.Product;
import com.shop.shop.validator.product.create.ProductCreateValidator;
import org.springframework.stereotype.Component;

@Component
public class ProductPriceValidator implements ProductCreateValidator {
    @Override
    public void validate(Product product) {
        if ((product.getPrice()==null)||(product.getPrice()<0)){
            throw new ProductPriceException("Wrong input. Price cannot be null");
        }
        /*if (product.getPrice()<0){
            throw new ProductPriceException("Wrong input. Price cannot be less than 0");
        }*/
    }
}
