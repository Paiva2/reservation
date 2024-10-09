package org.com.reservation.domain.usecase.common.exception;

public class RoomNotFoundException extends RuntimeException {
    private final static String MESSAGE = "Room not found!";

    public RoomNotFoundException() {
        super(MESSAGE);
    }
}
