package org.com.reservation.domain.usecase.reservation.makeReservation.exception;

import org.com.reservation.domain.common.exception.BadRequestException;

public class SessionAlreadyStartedException extends BadRequestException {
    private final static String MESSAGE = "Session already started!";

    public SessionAlreadyStartedException() {
        super(MESSAGE);
    }
}
