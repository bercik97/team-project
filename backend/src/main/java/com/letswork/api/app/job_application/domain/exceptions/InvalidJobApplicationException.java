package com.letswork.api.app.job_application.domain.exceptions;

import com.letswork.api.app.shared.ParameterizedException;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class InvalidJobApplicationException extends ParameterizedException {

    @Getter
    @AllArgsConstructor
    public enum CAUSE {
        MESSAGE_EMPTY("Wiadomość nie może być pusta"),
        MESSAGE_WRONG_LENGTH("Wiadomość powinna zawierać nie więcej niż 500 znaków");

        String message;
    }

    public InvalidJobApplicationException(InvalidJobApplicationException.CAUSE cause) {
        super(cause.message);
    }
}
