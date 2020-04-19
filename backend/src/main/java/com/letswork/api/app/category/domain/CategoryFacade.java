package com.letswork.api.app.category.domain;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@AllArgsConstructor
public class CategoryFacade {

    private final CategoryService service;

    public CategoryEntity findByCategoryName(String categoryName) {
        return service.findByCategoryName(categoryName);
    }

    @Cacheable("categories")
    public List<CategoryEntity> findAll() {
        return service.findAll();
    }
}
