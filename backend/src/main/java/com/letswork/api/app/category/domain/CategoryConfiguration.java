package com.letswork.api.app.category.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CategoryConfiguration {

    @Bean
    CategoryFacade categoryFacade(CategoryRepository repository) {
        CategoryValidator validator = new CategoryValidator();
        CategoryService service = new CategoryService(repository, validator);
        return new CategoryFacade(service);
    }
}
