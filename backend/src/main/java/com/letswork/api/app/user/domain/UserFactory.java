package com.letswork.api.app.user.domain;

import com.letswork.api.app.user.domain.dto.CreateUserDto;

class UserFactory {

    UserEntity create(CreateUserDto dto) {
        return UserEntity.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .isEnabled(false)
                .build();
    }
}
