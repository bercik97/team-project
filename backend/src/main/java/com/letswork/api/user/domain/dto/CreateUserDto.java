package com.letswork.api.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserDto {

    String email;
    String password;
    String confirmedPassword;
}
