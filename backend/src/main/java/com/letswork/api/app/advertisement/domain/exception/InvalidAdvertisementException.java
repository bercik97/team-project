package com.letswork.api.app.advertisement.domain.exception;

import com.letswork.api.app.shared.ParameterizedException;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class InvalidAdvertisementException extends ParameterizedException {

    @Getter
    @AllArgsConstructor
    public enum CAUSE {
        TITLE_EMPTY("Tytuł nie może być pusty"),
        TITLE_WRONG_LENGTH("Tytuł powinien zawierać maksymalnie 40 znaków"),
        CONTENT_EMPTY("Zawartość nie może być pusta"),
        CONTENT_WRONG_LENGTH("Zawartość powinna zawierać maksymalnie 1000 znaków"),
        ADVERTISEMENT_NOT_EXISTS("Ogłoszenie nie istnieje"),
        ADVERTISEMENT_CANNOT_BE_DELETED("Ogłoszenie nie może być usunięte");

        String message;
    }

    public InvalidAdvertisementException(CAUSE cause) {
        super(cause.message);
    }
}
