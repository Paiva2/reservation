package org.com.reservation.domain.usecase.session.createSession.exception;

import java.text.MessageFormat;
import java.util.List;

public class RoomsNotFoundException extends RuntimeException {
    private final static String MESSAGE_LIST = "Rooms {0} not found!";

    public RoomsNotFoundException(List<Long> roomsNotFound) {
        super(MessageFormat.format(MESSAGE_LIST, roomsNotFound));
    }
}
