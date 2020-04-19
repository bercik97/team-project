package com.letswork.api.app.category.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
class CategoryConfiguration {

    @Bean
    CategoryFacade categoryFacade(CategoryRepository repository) {
        CategoryValidator validator = new CategoryValidator();
        CategoryService service = new CategoryService(repository, validator);
        return new CategoryFacade(service);
    }

    CategoryFacade categoryFacade(ConcurrentHashMap<String, CategoryEntity> db) {
        CategoryInMemoryRepository repository = new CategoryInMemoryRepository(db);
        CategoryValidator validator = new CategoryValidator();
        CategoryService service = new CategoryService(repository, validator);
        return new CategoryFacade(service);
    }
}
