package com.letswork.api.token.domain;

import com.letswork.api.user.domain.UserEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TokenFacade {

    private final TokenService service;

    public void sendRegisterConfirmationToken(UserEntity user) {
        service.sendRegisterConfirmationToken(user);
    }
}
