package com.shop.shop.controller;

import com.shop.shop.dto.CategoryDTO;
import com.shop.shop.entity.Category;
import com.shop.shop.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/category")
//@Api(tags = "This is check for", description = " ApiOperation tags")
public class CategoryController {
    CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/all")
    public List<CategoryDTO> getAll(){
        List<Category> categories = categoryService.getAll() ;
        List<CategoryDTO> categoryDTOS = categories.stream().map(category -> new CategoryDTO(category)).collect(Collectors.toList());
        return categoryDTOS;
    }

    @PostMapping("/create")
    public CategoryDTO create (@RequestBody() CategoryDTO categoryDTO){
        Category category = categoryService.create(categoryDTO);
        return categoryService.map(category);
    }
    @PostMapping("/update")
    public CategoryDTO update (@RequestBody() CategoryDTO categoryDTO){
        Category category = categoryService.update(categoryDTO);
        return categoryService.map(category);
    }
    @ApiOperation("This method returns one category by id")
    @GetMapping("/{id}")
    public CategoryDTO getById(@PathVariable("id") Long id){
        Category category = categoryService.getOne(id);
        return categoryService.map(category);
    }
    @DeleteMapping({"/delete"})
    public ResponseEntity delete (@RequestBody() CategoryDTO categoryDTO){
        categoryService.delete(categoryDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}

