package com.letswork.api.app.category.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryEntity findByCategoryName(String categoryName) {
        return repository.findByName(categoryName);
    }
}
