package org.com.reservation.domain.usecase.common.exception;

import org.com.reservation.domain.common.exception.NotFoundException;

public class RoomNotFoundException extends NotFoundException {
    private final static String MESSAGE = "Room not found!";

    public RoomNotFoundException() {
        super(MESSAGE);
    }
}
