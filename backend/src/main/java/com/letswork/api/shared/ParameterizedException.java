package com.letswork.api.shared;

public abstract class ParameterizedException extends RuntimeException {

    public ParameterizedException(String message) {
        super(message);
    }
}
