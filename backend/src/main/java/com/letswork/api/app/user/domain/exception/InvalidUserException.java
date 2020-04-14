package com.letswork.api.app.user.domain.exception;

import com.letswork.api.app.shared.ParameterizedException;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class InvalidUserException extends ParameterizedException {

    @Getter
    @AllArgsConstructor
    public enum CAUSE {
        EMAIL_EMPTY("Proszę podać adres e-mail"),
        EMAIL_WRONG_FORMAT("Podany format adresu e-mail jest błędny"),
        EMAIL_NOT_UNIQUE("Podany adres e-mail istnieje już w systemie"),
        PASSWORD_EMPTY("Proszę podać hasło"),
        PASSWORD_WRONG_LENGTH("Hasło powinno zawierać conajmniej 6 znaków"),
        CONFIRMED_PASSWORD_DO_NOT_MATCH_PASSWORD("Podane hasła różnią się");

        String message;
    }

    public InvalidUserException(CAUSE cause) {
        super(cause.message);
    }
}
