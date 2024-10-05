package org.com.reservation.domain.usecase.user.common.exception;

public class InvalidEmailException extends RuntimeException {
    private final static String MESSAGE = "Invalid e-mail address!";

    public InvalidEmailException() {
        super(MESSAGE);
    }
}
