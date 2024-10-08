package org.com.reservation.application.config;

import org.com.reservation.domain.usecase.common.exception.InvalidDateException;
import org.com.reservation.domain.usecase.common.exception.MovieNotFoundException;
import org.com.reservation.domain.usecase.movie.registerMovie.exception.GenresNotFoundException;
import org.com.reservation.domain.usecase.movie.registerMovie.exception.MovieTitleAlreadyExistsException;
import org.com.reservation.domain.usecase.session.createSession.exception.RoomsNotFoundException;
import org.com.reservation.domain.usecase.session.createSession.exception.SessionPeriodUnavailableException;
import org.com.reservation.domain.usecase.user.common.InvalidPermissionsException;
import org.com.reservation.domain.usecase.user.common.exception.InvalidEmailException;
import org.com.reservation.domain.usecase.user.common.exception.UserNotFoundException;
import org.com.reservation.domain.usecase.user.registerUser.exception.EmailAlreadyUsedException;
import org.com.reservation.domain.usecase.user.registerUser.exception.InvalidPasswordException;
import org.com.reservation.domain.usecase.user.userAuthentication.exception.ErrorSigningAuthenticationException;
import org.com.reservation.domain.usecase.user.userAuthentication.exception.InvalidCredentialsException;
import org.com.reservation.domain.usecase.user.userAuthentication.exception.UserDisabledException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class RestControllerExceptionConfig {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(mapErrors(ex, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

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

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(mapErrors(ex, HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        return new ResponseEntity<>(mapErrors(ex, HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserDisabledException.class)
    public ResponseEntity<Map<String, String>> handleUserDisabledException(UserDisabledException ex) {
        return new ResponseEntity<>(mapErrors(ex, HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ErrorSigningAuthenticationException.class)
    public ResponseEntity<Map<String, String>> handleErrorSigningAuthenticationException(ErrorSigningAuthenticationException ex) {
        return new ResponseEntity<>(mapErrors(ex, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidPermissionsException.class)
    public ResponseEntity<Map<String, String>> handleInvalidPermissionsException(InvalidPermissionsException ex) {
        return new ResponseEntity<>(mapErrors(ex, HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MovieTitleAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleMovieTitleAlreadyExistsException(MovieTitleAlreadyExistsException ex) {
        return new ResponseEntity<>(mapErrors(ex, HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(GenresNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleGenresNotFoundException(GenresNotFoundException ex) {
        return new ResponseEntity<>(mapErrors(ex, HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<Map<String, String>> handleInvalidDateException(InvalidDateException ex) {
        return new ResponseEntity<>(mapErrors(ex, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleMovieNotFoundException(MovieNotFoundException ex) {
        return new ResponseEntity<>(mapErrors(ex, HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoomsNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRoomsNotFoundException(RoomsNotFoundException ex) {
        return new ResponseEntity<>(mapErrors(ex, HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SessionPeriodUnavailableException.class)
    public ResponseEntity<Map<String, String>> handleSessionPeriodUnavailableException(SessionPeriodUnavailableException ex) {
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
