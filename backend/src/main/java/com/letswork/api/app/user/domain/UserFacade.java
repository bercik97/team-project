package com.letswork.api.app.user.domain;

import com.letswork.api.app.user.domain.dto.CreateUserDto;
import com.letswork.api.app.user.domain.dto.SignInDto;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UserFacade {

    private final UserService service;

    public void create(CreateUserDto dto) {
        service.create(dto);
    }

    public void confirmAccount(String confirmationToken) {
        service.confirmAccount(confirmationToken);
    }

    public Optional<SignInDto> findByEmailToSignIn(String email) {
        return service.findByEmailToSignIn(email);
    }
}
