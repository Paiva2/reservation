package org.com.reservation.domain.usecase.common.exception;

import org.com.reservation.domain.common.exception.NotFoundException;

public class ReservationNotFoundException extends NotFoundException {
    private static final String MESSAGE = "Reservation not found!";

    public ReservationNotFoundException() {
        super(MESSAGE);
    }
}
