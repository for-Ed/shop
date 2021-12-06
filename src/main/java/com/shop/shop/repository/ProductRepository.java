package com.shop.shop.repository;

import com.shop.shop.entity.Category;
import com.shop.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> getAllByCategoryIn(List<Category> categories);

    /* @Query(value = "SELECT * FROM product WHERE category_id in (:categories)", nativeQuery = true)
    List<Product> getAllByCategoryIn1(List<Category> categories);*/
}