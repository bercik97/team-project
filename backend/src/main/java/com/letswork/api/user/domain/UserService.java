package com.letswork.api.user.domain;

import com.letswork.api.user.domain.dto.CreateUserDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class UserService {

    private final UserRepository repository;
    private final UserFactory factory;

    void create(CreateUserDto dto) {
        repository.save(factory.create(dto));
    }
}
