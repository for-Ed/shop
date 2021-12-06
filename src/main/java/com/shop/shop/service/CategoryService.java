package com.shop.shop.service;

import com.shop.shop.dto.CategoryDTO;
import com.shop.shop.entity.Category;

import java.util.List;


public interface CategoryService {
    List<Category> getAll();

    Category create(CategoryDTO categoryDTO);

    void delete (CategoryDTO categoryDTO);

    Category update (CategoryDTO categoryDTO);

    CategoryDTO map(Category category);

    Category map(CategoryDTO categoryDTO);

    Category getOne(Long id);

}
