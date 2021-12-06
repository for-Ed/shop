package com.shop.shop.repository;

import com.shop.shop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Locale;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
