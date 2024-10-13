package org.com.reservation.domain.usecase.session.getAvailableRoomSeatsOnSession.exception;

public class RoomSessionNotFoundException extends RuntimeException {
    private final static String MESSAGE = "Session in this room not found!";

    public RoomSessionNotFoundException() {
        super(MESSAGE);
    }
}
