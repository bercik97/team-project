package com.letswork.api.token.domain.exception;

import com.letswork.api.shared.ParameterizedException;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class InvalidTokenException extends ParameterizedException {

    @Getter
    @AllArgsConstructor
    public enum CAUSE {
        CONFIRMATION_TOKEN_NOT_SENT("Token potwierdzający nie został wysłany"),
        CONFIRMATION_TOKEN_EXPIRED("Token potwierdzający jest nieaktywny");

        String message;
    }

    public InvalidTokenException(CAUSE cause) {
        super(cause.message);
    }
}
