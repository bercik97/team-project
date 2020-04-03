package com.letswork.api.app.token.domain.exception;

import com.letswork.api.app.shared.ParameterizedException;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class InvalidTokenException extends ParameterizedException {

    @Getter
    @AllArgsConstructor
    public enum CAUSE {
        CONFIRMATION_TOKEN_NOT_SENT("Token potwierdzający nie został wysłany"),
        CONFIRMATION_TOKEN_NOT_EXISTS("Token potwierdzający nie istnieje");

        String message;
    }

    public InvalidTokenException(CAUSE cause) {
        super(cause.message);
    }
}
