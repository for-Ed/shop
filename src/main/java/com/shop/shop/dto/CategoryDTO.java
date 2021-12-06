package com.shop.shop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.shop.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long id;

    private String name;

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
    /* @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Long> products; */
}
