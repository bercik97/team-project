package com.letswork.api.app.category.domain.exception;

import com.letswork.api.app.shared.ParameterizedException;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class InvalidCategoryException extends ParameterizedException {

    @Getter
    @AllArgsConstructor
    public enum CAUSE {
        CATEGORY_NAME_EMPTY("Kategoria nie może być pusta"),
        CATEGORY_NAME_NOT_EXISTS("Kategoria nie istnieje");

        String message;
    }

    public InvalidCategoryException(InvalidCategoryException.CAUSE cause) {
        super(cause.message);
    }
}
