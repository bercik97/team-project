package com.letswork.api.app.category.domain;

import com.google.common.base.Strings;
import com.letswork.api.app.category.domain.exception.InvalidCategoryException;

class CategoryValidator {

    public void validateCategoryName(String categoryName) {
        InvalidCategoryException.CAUSE cause = null;

        if (Strings.isNullOrEmpty(categoryName) || categoryName.isBlank()) {
            cause = InvalidCategoryException.CAUSE.CATEGORY_NAME_EMPTY;
        } else if (isCategoryDoNotExists(categoryName)) {
            cause = InvalidCategoryException.CAUSE.CATEGORY_NAME_NOT_EXISTS;
        }

        if (cause != null) {
            throw new InvalidCategoryException(cause);
        }
    }

    private boolean isCategoryDoNotExists(String categoryName) {
        for (CategoryType category : CategoryType.values()) {
            if (category.name.equalsIgnoreCase(categoryName)) {
                return false;
            }
        }
        return true;
    }
}
