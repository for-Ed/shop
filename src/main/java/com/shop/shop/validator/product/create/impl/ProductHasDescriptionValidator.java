package com.shop.shop.validator.product.create.impl;

import com.shop.shop.configuration.exception.product.ProductDoesNotHaveDescriptionException;
import com.shop.shop.entity.Product;
import com.shop.shop.validator.product.create.ProductCreateValidator;
import org.springframework.stereotype.Component;

@Component
public class ProductHasDescriptionValidator implements ProductCreateValidator {
    @Override
    public void validate(Product product) {
        if (product.getDescription()==null||product.getDescription().isEmpty()){
            throw new ProductDoesNotHaveDescriptionException("Wrong input. Cannot create product with no description");
        }
    }
}
