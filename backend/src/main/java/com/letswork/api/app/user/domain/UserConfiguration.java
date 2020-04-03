package com.letswork.api.app.user.domain;

import com.letswork.api.app.token.domain.TokenFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
class UserConfiguration {

    @Bean
    UserFacade userFacade(UserRepository repository,
                          TokenFacade tokenFacade) {
        UserFactory factory = new UserFactory();
        UserValidator validator = new UserValidator(repository);
        UserService service = new UserService(repository, factory, validator, tokenFacade);
        return new UserFacade(service);
    }

    UserFacade userFacade(ConcurrentHashMap<String, UserEntity> db,
                          TokenFacade tokenFacade) {
        UserInMemoryRepository repository = new UserInMemoryRepository(db);
        UserFactory factory = new UserFactory();
        UserValidator validator = new UserValidator(repository);
        UserService service = new UserService(repository, factory, validator, tokenFacade);
        return new UserFacade(service);
    }
}
