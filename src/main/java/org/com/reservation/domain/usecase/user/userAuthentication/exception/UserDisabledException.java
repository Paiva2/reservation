package org.com.reservation.domain.usecase.user.userAuthentication.exception;

public class UserDisabledException extends RuntimeException {
    private static final String MESSAGE = "User disabled!";

    public UserDisabledException() {
        super(MESSAGE);
    }
}
