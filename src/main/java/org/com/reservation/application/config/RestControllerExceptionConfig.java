package org.com.reservation.application.config;

import org.com.reservation.domain.usecase.user.common.exception.InvalidEmailException;
import org.com.reservation.domain.usecase.user.registerUser.exception.EmailAlreadyUsedException;
import org.com.reservation.domain.usecase.user.registerUser.exception.InvalidPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class RestControllerExceptionConfig {
    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<Map<String, String>> handleInvalidEmailException(InvalidEmailException ex) {
        return new ResponseEntity<>(mapErrors(ex, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Map<String, String>> handleInvalidPasswordException(InvalidPasswordException ex) {
        return new ResponseEntity<>(mapErrors(ex, HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyUsedException(EmailAlreadyUsedException ex) {
        return new ResponseEntity<>(mapErrors(ex, HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT);
    }

    private Map<String, String> mapErrors(Exception ex, int statusValue) {
        return new LinkedHashMap<>() {{
            put("date", new Date().toString());
            put("status", String.valueOf(statusValue));
            put("message", ex.getMessage());
            put("exception", ex.getClass().getSimpleName());
        }};
    }
}
