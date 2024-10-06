package org.com.reservation.domain.usecase.user.registerUser.exception;

public class InvalidPasswordException extends RuntimeException {
    private final static String MESSAGE = "Invalid password size. Minimum size is 6 characters!";

    public InvalidPasswordException() {
        super(MESSAGE);
    }
}
