package org.com.reservation.domain.usecase.common.exception;

public class UserNotFoundException extends RuntimeException {
    private static final String MESSAGE = "User not found!";

    public UserNotFoundException() {
        super(MESSAGE);
    }
}
