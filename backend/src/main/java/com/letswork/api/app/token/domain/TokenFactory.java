package com.letswork.api.app.token.domain;

import com.letswork.api.app.user.domain.UserEntity;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

class TokenFactory {

    TokenEntity create(UserEntity userEntity) {
        return TokenEntity.builder()
                .confirmationToken(UUID.randomUUID().toString())
                .dateInSeconds(String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())))
                .user(userEntity)
                .build();
    }
}
