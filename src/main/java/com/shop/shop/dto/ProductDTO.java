package com.shop.shop.dto;

import com.shop.shop.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {

    private Long id;

    private String name;

    private String description;

    private Long price;

    private CategoryDTO category;

    private Long capacity;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.category = new CategoryDTO(product.getCategory());
        this.capacity = product.getCapacity();
    }
}
