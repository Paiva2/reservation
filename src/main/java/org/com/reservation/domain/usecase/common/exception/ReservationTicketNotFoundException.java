package org.com.reservation.domain.usecase.common.exception;

import org.com.reservation.domain.common.exception.NotFoundException;

public class ReservationTicketNotFoundException extends NotFoundException {
    private static final String MESSAGE = "Reservation ticket not found!";

    public ReservationTicketNotFoundException() {
        super(MESSAGE);
    }
}
