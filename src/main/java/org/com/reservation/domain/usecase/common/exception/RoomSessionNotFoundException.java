package org.com.reservation.domain.usecase.common.exception;

import org.com.reservation.domain.common.exception.NotFoundException;

public class RoomSessionNotFoundException extends NotFoundException {
    private final static String MESSAGE = "Session in this room not found!";

    public RoomSessionNotFoundException() {
        super(MESSAGE);
    }
}
