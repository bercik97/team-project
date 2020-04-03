package com.letswork.api.app.shared;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ParameterizedException.class)
    public ResponseEntity<Object> handleParameterizedException(RuntimeException e) {
        return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
