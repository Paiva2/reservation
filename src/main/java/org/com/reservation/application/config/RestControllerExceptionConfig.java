package org.com.reservation.application.config;

import org.com.reservation.domain.usecase.user.common.exception.InvalidEmailException;
import org.com.reservation.domain.usecase.user.registerUserUsecase.exception.EmailAlreadyUsedException;
import org.com.reservation.domain.usecase.user.registerUserUsecase.exception.InvalidPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestControllerExceptionConfig {
    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<Map<String, String>> handleInvalidEmailException(InvalidEmailException ex) {
        Map<String, String> errorMapper = new HashMap<>() {{
            put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
            put("message", ex.getMessage());
        }};
        return new ResponseEntity<>(errorMapper, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Map<String, String>> handleInvalidPasswordException(InvalidPasswordException ex) {
        Map<String, String> errorMapper = new HashMap<>() {{
            put("status", String.valueOf(HttpStatus.CONFLICT.value()));
            put("message", ex.getMessage());
        }};
        return new ResponseEntity<>(errorMapper, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyUsedException(EmailAlreadyUsedException ex) {
        Map<String, String> errorMapper = new HashMap<>() {{
            put("status", String.valueOf(HttpStatus.CONFLICT.value()));
            put("message", ex.getMessage());
        }};
        return new ResponseEntity<>(errorMapper, HttpStatus.CONFLICT);
    }
}
