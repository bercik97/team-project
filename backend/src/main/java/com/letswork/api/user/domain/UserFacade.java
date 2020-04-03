package com.letswork.api.user.domain;

import com.letswork.api.user.domain.dto.CreateUserDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserFacade {

    private final UserService service;

    public void create(CreateUserDto dto) {
        service.create(dto);
    }

    public void confirmAccount(String confirmationToken) {
        service.confirmAccount(confirmationToken);
    }
}
