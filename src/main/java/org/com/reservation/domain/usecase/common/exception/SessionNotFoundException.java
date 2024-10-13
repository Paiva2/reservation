package org.com.reservation.domain.usecase.common.exception;

public class SessionNotFoundException extends RuntimeException {
    private final static String MESSAGE = "Session not found!";

    public SessionNotFoundException() {
        super(MESSAGE);
    }
}
