package com.letswork.api.user.domain.dto;

import lombok.Getter;

@Getter
public class CreateUserDto {

    String email;
    String password;
    String confirmedPassword;
}
