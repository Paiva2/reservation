package org.com.reservation.domain.usecase.user.userAuthentication.exception;

import org.com.reservation.domain.common.exception.ForbiddenException;

public class InvalidCredentialsException extends ForbiddenException {
    private final static String MESSAGE = "Invalid credentials.";

    public InvalidCredentialsException() {
        super(MESSAGE);
    }
}
