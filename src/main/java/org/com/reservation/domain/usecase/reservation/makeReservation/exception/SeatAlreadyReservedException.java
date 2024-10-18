package org.com.reservation.domain.usecase.reservation.makeReservation.exception;

import org.com.reservation.domain.common.exception.ConflictException;

import java.text.MessageFormat;
import java.util.List;

public class SeatAlreadyReservedException extends ConflictException {
    public static final String MESSAGE = "Seat {0} on room {1} already reserved for this session!";
    public static final String MESSAGE_MANY = "Seats {0} on room {1} already reserved for this session!";

    public SeatAlreadyReservedException(String seatId, String roomId) {
        super(MessageFormat.format(MESSAGE, seatId, roomId));
    }

    public SeatAlreadyReservedException(List<Long> seatsIds, String roomId) {
        super(MessageFormat.format(MESSAGE_MANY, seatsIds, roomId));
    }
}
