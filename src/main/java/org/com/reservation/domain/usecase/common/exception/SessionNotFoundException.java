package org.com.reservation.domain.usecase.common.exception;

import org.com.reservation.domain.common.exception.NotFoundException;

public class SessionNotFoundException extends NotFoundException {
    private final static String MESSAGE = "Session not found!";

    public SessionNotFoundException() {
        super(MESSAGE);
    }
}
