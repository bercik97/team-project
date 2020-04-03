package com.letswork.api.app.token.domain;

import com.letswork.api.app.user.domain.UserEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TokenFacade {

    private final TokenService service;

    public void sendRegisterConfirmationToken(UserEntity user) {
        service.sendRegisterConfirmationToken(user);
    }

    public void cleanAllExpiredTokens() {
        service.cleanAllExpiredTokens();
    }

    public TokenEntity findTokenByConfirmationToken(String confirmationToken) {
        return service.findTokenByConfirmationToken(confirmationToken);
    }

    public void deleteToken(TokenEntity token) {
        service.deleteToken(token);
    }
}
