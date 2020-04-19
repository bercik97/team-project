package com.letswork.api.app.category.domain;

import org.springframework.data.repository.Repository;

import java.util.List;

interface CategoryRepository extends Repository<CategoryEntity, Long> {

    CategoryEntity findByName(String name);

    List<CategoryEntity> findAll();
}
