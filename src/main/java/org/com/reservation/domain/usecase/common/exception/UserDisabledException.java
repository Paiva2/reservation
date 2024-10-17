package org.com.reservation.domain.usecase.common.exception;

public class UserDisabledException extends RuntimeException {
    private static final String MESSAGE = "User disabled!";

    public UserDisabledException() {
        super(MESSAGE);
    }
}
