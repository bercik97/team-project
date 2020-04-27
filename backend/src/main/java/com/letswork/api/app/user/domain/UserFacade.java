package com.letswork.api.app.user.domain;

import com.letswork.api.app.user.domain.dto.CreateUserDto;
import com.letswork.api.app.user.domain.dto.SignInDto;
import lombok.AllArgsConstructor;

import javax.transaction.Transactional;
import java.util.Optional;

@AllArgsConstructor
public class UserFacade {

    private final UserService service;

    @Transactional
    public void create(CreateUserDto dto) {
        service.create(dto);
    }

    public void confirmAccount(String confirmationToken) {
        service.confirmAccount(confirmationToken);
    }

    public Optional<SignInDto> findByEmailToSignIn(String email) {
        return service.findByEmailToSignIn(email);
    }

    public UserEntity findByEmail(String email) {
        return service.findByEmail(email);
    }
}
