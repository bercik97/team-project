package com.letswork.api.app.job_application.domain;

import com.google.common.base.Strings;
import com.letswork.api.app.job_application.domain.exceptions.InvalidJobApplicationException;

class JobApplicationValidator {

    public void validate(String message) {
        InvalidJobApplicationException.CAUSE cause = null;

        if (Strings.isNullOrEmpty(message) || message.isBlank()) {
            cause = InvalidJobApplicationException.CAUSE.MESSAGE_EMPTY;
        } else if (message.length() > 500) {
            cause = InvalidJobApplicationException.CAUSE.MESSAGE_WRONG_LENGTH;
        }

        if (cause != null) {
            throw new InvalidJobApplicationException(cause);
        }
    }
}
