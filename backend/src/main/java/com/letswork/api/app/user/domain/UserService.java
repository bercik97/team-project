package com.letswork.api.app.user.domain;

import com.letswork.api.app.token.domain.TokenEntity;
import com.letswork.api.app.token.domain.TokenFacade;
import com.letswork.api.app.token.domain.exception.InvalidTokenException;
import com.letswork.api.app.user.domain.dto.CreateUserDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserFactory factory;
    private final UserValidator validator;
    private final TokenFacade tokenFacade;

    void create(CreateUserDto dto) {
        validator.validate(dto);
        UserEntity user = factory.create(dto);
        repository.save(user);
        try {
            tokenFacade.sendRegisterConfirmationToken(user);
        } catch (InvalidTokenException exception) {
            repository.delete(user);
            throw exception;
        }
    }

    public void confirmAccount(String confirmationToken) {
        tokenFacade.cleanAllExpiredTokens();
        TokenEntity token = tokenFacade.findTokenByConfirmationToken(confirmationToken);
        UserEntity user = token.getUser();
        enableUserAccount(user);
        tokenFacade.deleteToken(token);
    }

    private void enableUserAccount(UserEntity user) {
        user.setEnabled(true);
        repository.save(user);
    }
}
