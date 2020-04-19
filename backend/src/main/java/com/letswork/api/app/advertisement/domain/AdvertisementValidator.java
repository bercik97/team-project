package com.letswork.api.app.advertisement.domain;

import com.google.common.base.Strings;
import com.letswork.api.app.advertisement.domain.dto.CreateAdvertisementDto;
import com.letswork.api.app.advertisement.domain.exception.InvalidAdvertisementException;

class AdvertisementValidator {

    public void validate(CreateAdvertisementDto dto) {
        validateTitle(dto.getTitle());
        validateContent(dto.getContent());
    }

    private void validateTitle(String title) {
        InvalidAdvertisementException.CAUSE cause = null;

        if (Strings.isNullOrEmpty(title) || title.isBlank()) {
            cause = InvalidAdvertisementException.CAUSE.TITLE_EMPTY;
        } else if (title.length() > 40) {
            cause = InvalidAdvertisementException.CAUSE.TITLE_WRONG_LENGTH;
        }

        if (cause != null) {
            throw new InvalidAdvertisementException(cause);
        }
    }

    private void validateContent(String content) {
        InvalidAdvertisementException.CAUSE cause = null;

        if (Strings.isNullOrEmpty(content) || content.isBlank()) {
            cause = InvalidAdvertisementException.CAUSE.CONTENT_EMPTY;
        } else if (content.length() > 1000) {
            cause = InvalidAdvertisementException.CAUSE.CONTENT_WRONG_LENGTH;
        }

        if (cause != null) {
            throw new InvalidAdvertisementException(cause);
        }
    }
}
