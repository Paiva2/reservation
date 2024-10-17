package org.com.reservation.domain.usecase.reservation.makeReservation.exception;

import java.text.MessageFormat;

public class RoomSeatNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Seat not found in room!";
    private static final String MESSAGE_MANY = "Seats {0} not found in room {1}!";

    public RoomSeatNotFoundException() {
        super(MESSAGE);
    }

    public RoomSeatNotFoundException(String seats, String room) {
        super(MessageFormat.format(MESSAGE_MANY, seats, room));
    }
}
