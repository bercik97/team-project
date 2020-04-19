package com.letswork.api.app.category.domain;

import com.letswork.api.app.category.domain.exception.InvalidCategoryException;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
class CategoryInMemoryRepository implements CategoryRepository {

    private ConcurrentHashMap<String, CategoryEntity> map;

    @Override
    public CategoryEntity findByName(String name) {
        return map.values()
                .stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElseThrow(() ->
                        new InvalidCategoryException(InvalidCategoryException.CAUSE.CATEGORY_NAME_NOT_EXISTS));
    }

    @Override
    public List<CategoryEntity> findAll() {
        return new ArrayList<>(map.values());
    }
}

