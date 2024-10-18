package org.com.reservation.domain.usecase.common.exception;

import org.com.reservation.domain.common.exception.ForbiddenException;

public class UserDisabledException extends ForbiddenException {
    private static final String MESSAGE = "User disabled!";

    public UserDisabledException() {
        super(MESSAGE);
    }
}
