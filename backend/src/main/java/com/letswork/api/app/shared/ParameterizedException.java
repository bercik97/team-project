package com.letswork.api.app.shared;

public abstract class ParameterizedException extends RuntimeException {

    public ParameterizedException(String message) {
        super(message);
    }
}
