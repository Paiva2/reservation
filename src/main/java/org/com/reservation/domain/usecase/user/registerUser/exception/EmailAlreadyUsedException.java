package org.com.reservation.domain.usecase.user.registerUser.exception;

public class EmailAlreadyUsedException extends RuntimeException {
    private final static String MESSAGE = "Provided e-mail address is already in use!";

    public EmailAlreadyUsedException() {
        super(MESSAGE);
    }
}
