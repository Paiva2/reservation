package org.com.reservation.domain.usecase.common.exception;

public class MovieTicketNotFoundException extends RuntimeException {
    private final static String MESSAGE = "Movie Ticket not found exception!";

    public MovieTicketNotFoundException() {
        super(MESSAGE);
    }
}
