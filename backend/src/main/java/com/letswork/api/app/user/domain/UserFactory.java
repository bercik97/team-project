package com.letswork.api.app.user.domain;

import com.letswork.api.app.user.domain.dto.CreateUserDto;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserFactory {

    UserEntity create(CreateUserDto dto) {
        return UserEntity.builder()
                .email(dto.getEmail())
                .password(encoder().encode(dto.getPassword()))
                .isEnabled(false)
                .build();
    }

    @Bean
    static PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
