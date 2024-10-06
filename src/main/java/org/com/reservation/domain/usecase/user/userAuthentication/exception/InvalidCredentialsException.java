package org.com.reservation.domain.usecase.user.userAuthentication.exception;

public class InvalidCredentialsException extends RuntimeException {
    private final static String MESSAGE = "Invalid credentials.";

    public InvalidCredentialsException() {
        super(MESSAGE);
    }
}
