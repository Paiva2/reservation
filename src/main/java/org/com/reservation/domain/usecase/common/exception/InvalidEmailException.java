package org.com.reservation.domain.usecase.common.exception;

import org.com.reservation.domain.common.exception.BadRequestException;

public class InvalidEmailException extends BadRequestException {
    private final static String MESSAGE = "Invalid e-mail address!";

    public InvalidEmailException() {
        super(MESSAGE);
    }
}
