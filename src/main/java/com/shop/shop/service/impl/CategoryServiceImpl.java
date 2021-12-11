package com.shop.shop.service.impl;

import com.shop.shop.configuration.exception.category.CategoryNotFoundException;
import com.shop.shop.dto.CategoryDTO;
import com.shop.shop.entity.Category;
import com.shop.shop.repository.CategoryRepository;
import com.shop.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl  implements CategoryService {

    private CategoryRepository repository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public Category create(CategoryDTO categoryDTO) {
        //todo: need some exception + add validators
        Category category = map(categoryDTO);
        return repository.save(category);
    }

    @Override
    public void delete(CategoryDTO categoryDTO) {
        repository.deleteById(categoryDTO.getId());
    }

    @Override
    public Category update(CategoryDTO categoryDTO) {
        if(categoryDTO.getId()==null){
            throw new IllegalArgumentException("ID must not be null!");
        }
        Category category = map(categoryDTO);
        return repository.save(category);
    }

    @Override
    public CategoryDTO map(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }

    @Override
    public Category map(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        return category;
    }

    @Override
    public Category getOne(Long id) {
        Optional<Category> byId = repository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        throw  new CategoryNotFoundException("Product with id " + id + " not found");
    }
}
