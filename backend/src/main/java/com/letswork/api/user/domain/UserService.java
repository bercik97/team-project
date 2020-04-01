package com.letswork.api.user.domain;

import com.letswork.api.token.domain.TokenFacade;
import com.letswork.api.user.domain.dto.CreateUserDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class UserService {

    private final UserRepository repository;
    private final UserFactory factory;
    private final UserValidator validator;
    private final TokenFacade tokenFacade;

    void create(CreateUserDto dto) {
        validator.validate(dto);
        repository.save(factory.create(dto));
    }
}
