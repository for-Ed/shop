package com.shop.shop.validator.product.create.impl;

import com.shop.shop.configuration.exception.product.ProductDoesNotHaveNameException;
import com.shop.shop.entity.Product;
import com.shop.shop.validator.product.create.ProductCreateValidator;
import org.springframework.stereotype.Component;

@Component
public class ProductHasNameValidator implements ProductCreateValidator {

    @Override
    public void validate(Product product) {
        if(product.getName()==null||product.getName().isEmpty()){
            throw new ProductDoesNotHaveNameException("Wrong input. Cannot create product with no name");
        }
    }
}