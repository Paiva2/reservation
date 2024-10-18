package org.com.reservation.domain.usecase.common.exception;

import org.com.reservation.domain.common.exception.NotFoundException;

public class MovieTicketNotFoundException extends NotFoundException {
    private final static String MESSAGE = "Movie Ticket not found exception!";

    public MovieTicketNotFoundException() {
        super(MESSAGE);
    }
}
