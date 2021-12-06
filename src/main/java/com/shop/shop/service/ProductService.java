package com.shop.shop.service;

import com.shop.shop.dto.CategoryDTO;
import com.shop.shop.dto.ProductDTO;
import com.shop.shop.entity.Category;
import com.shop.shop.entity.Product;

import java.util.List;

public interface ProductService {
    Product getOne(Long id);

    List<Product> getAll();

    List<Product> getAllByCategories(List<CategoryDTO> categories);

    Product save(Product product);

    Product create(ProductDTO productDTO);

    Product update (ProductDTO productDTO);

    void delete (Long id);

    Product map(ProductDTO productDTO);

    ProductDTO map(Product product);


}

