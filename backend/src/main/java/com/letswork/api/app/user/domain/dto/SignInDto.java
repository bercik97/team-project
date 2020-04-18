package com.letswork.api.app.user.domain.dto;

import com.letswork.api.app.user.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignInDto {

    private String email;
    private String password;
    private boolean isEnabled;

    public static SignInDto convert(UserEntity entity) {
        return SignInDto.builder()
                .email(entity.getEmail())
                .password(entity.getPassword())
                .isEnabled(entity.isEnabled())
                .build();
    }
}
