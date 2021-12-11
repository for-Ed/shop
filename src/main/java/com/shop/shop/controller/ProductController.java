package com.shop.shop.controller;


import com.shop.shop.dto.CategoryDTO;
import com.shop.shop.dto.ProductDTO;
import com.shop.shop.entity.Product;
import com.shop.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    private ProductDTO getOne(@PathVariable(name="id")Long id){
        Product product = productService.getOne(id);
        return productService.map(product);
    }
    /*  @RequestMapping(value = "/{id}", method = RequestMethod.GET) or @GetMapping ("/{id}")*/

    @PostMapping("/create")
    private ProductDTO create(@RequestBody() ProductDTO productDTO){
    return productService.map(productService.create(productDTO));
    }

    @PostMapping("/update")
    private ProductDTO update(@RequestBody() ProductDTO productDTO){
        return productService.map(productService.update(productDTO));
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/all")
    private List<ProductDTO> getAll(){
        List<Product> products = productService.getAll();
        List<ProductDTO> productDTOS = products.stream().map(product -> productService.map(product)).collect(Collectors.toList());
        return productDTOS;
    }
    @GetMapping("/category")
    private List<ProductDTO> getByCategories(@RequestBody()List<CategoryDTO> categoryDTOS){
        List<Product> allByCategories = productService.getAllByCategories(categoryDTOS);
        return  allByCategories.stream().map(product -> productService.map(product)).collect(Collectors.toList());
    }

    @DeleteMapping("/delete")
    private ResponseEntity delete(@PathVariable(name = "id")Long id){
        productService.delete(id);
        return new ResponseEntity(HttpStatus.OK);

    }
}

