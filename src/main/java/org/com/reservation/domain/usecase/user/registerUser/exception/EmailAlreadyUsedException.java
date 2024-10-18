package org.com.reservation.domain.usecase.user.registerUser.exception;

import org.com.reservation.domain.common.exception.ConflictException;

public class EmailAlreadyUsedException extends ConflictException {
    private final static String MESSAGE = "Provided e-mail address is already in use!";

    public EmailAlreadyUsedException() {
        super(MESSAGE);
    }
}
