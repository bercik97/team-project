package com.letswork.api.app.category.domain;

import org.springframework.data.repository.Repository;

interface CategoryRepository extends Repository<CategoryEntity, Long> {

    CategoryEntity findByName(String name);
}
