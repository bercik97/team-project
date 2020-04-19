package com.letswork.api.app.category.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CategoryConfiguration {

    @Bean
    CategoryFacade categoryFacade(CategoryRepository repository) {
        CategoryService service = new CategoryService(repository);
        return new CategoryFacade(service);
    }
}
