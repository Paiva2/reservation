package org.com.reservation.domain.usecase.reservation.makeReservation.exception;

public class SessionAlreadyStartedException extends RuntimeException {
    private final static String MESSAGE = "Session already started!";

    public SessionAlreadyStartedException() {
        super(MESSAGE);
    }
}
