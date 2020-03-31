package com.letswork.api.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
class UserConfiguration {

    @Bean
    UserFacade facade(UserRepository repository) {
        UserService service = new UserService(repository, new UserFactory(), new UserValidator(repository));
        return new UserFacade(service);
    }

    UserFacade facade(ConcurrentHashMap<String, UserEntity> db) {
        UserInMemoryRepository repository = new UserInMemoryRepository(db);
        UserService service = new UserService(repository, new UserFactory(), new UserValidator(repository));
        return new UserFacade(service);
    }
}
