package com.letswork.api.user.domain;

import com.google.common.base.Strings;
import com.letswork.api.user.domain.dto.CreateUserDto;
import com.letswork.api.user.domain.exception.InvalidUserException;
import lombok.AllArgsConstructor;

import java.util.regex.Pattern;

@AllArgsConstructor
class UserValidator {

    private static final Pattern EMAIL_REGEX =
            Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*" +
                    "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private final UserRepository repository;

    public void validate(CreateUserDto dto) {
        validateEmail(dto.getEmail());
        validatePasswords(dto.getPassword(), dto.getConfirmedPassword());
    }

    private void validateEmail(String email) {
        InvalidUserException.CAUSE cause = null;

        if (Strings.isNullOrEmpty(email) || email.isBlank()) {
            cause = InvalidUserException.CAUSE.EMAIL_EMPTY;
        } else if (!EMAIL_REGEX.matcher(email).find()) {
            cause = InvalidUserException.CAUSE.EMAIL_WRONG_FORMAT;
        } else if (isEmailExists(email)) {
            cause = InvalidUserException.CAUSE.EMAIL_NOT_UNIQUE;
        }

        if (cause != null) {
            throw new InvalidUserException(cause);
        }
    }

    private boolean isEmailExists(String email) {
        return repository.existsByEmail(email);
    }

    private void validatePasswords(String password, String confirmedPassword) {
        InvalidUserException.CAUSE cause = null;

        if (Strings.isNullOrEmpty(password) || password.isBlank()) {
            cause = InvalidUserException.CAUSE.PASSWORD_EMPTY;
        } else if (password.length() < 6) {
            cause = InvalidUserException.CAUSE.PASSWORD_WRONG_LENGTH;
        } else if (!confirmedPassword.equals(password)) {
            cause = InvalidUserException.CAUSE.CONFIRMED_PASSWORD_DO_NOT_MATCH_PASSWORD;
        }

        if (cause != null) {
            throw new InvalidUserException(cause);
        }
    }
}
