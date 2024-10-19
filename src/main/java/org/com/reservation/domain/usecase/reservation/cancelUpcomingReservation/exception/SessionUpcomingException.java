package org.com.reservation.domain.usecase.reservation.cancelUpcomingReservation.exception;

import org.com.reservation.domain.common.exception.BadRequestException;

public class SessionUpcomingException extends BadRequestException {
    public SessionUpcomingException(String message) {
        super(message);
    }
}
