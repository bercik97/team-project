package com.letswork.api.app.category.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CategoryFacade {

    private final CategoryService service;

    public CategoryEntity findByCategoryName(String categoryName) {
        return service.findByCategoryName(categoryName);
    }
}
