package org.com.reservation.domain.usecase.session.createSession.exception;

import org.com.reservation.domain.common.exception.NotFoundException;

import java.text.MessageFormat;
import java.util.List;

public class RoomsNotFoundException extends NotFoundException {
    private final static String MESSAGE_LIST = "Rooms {0} not found!";

    public RoomsNotFoundException(List<Long> roomsNotFound) {
        super(MessageFormat.format(MESSAGE_LIST, roomsNotFound));
    }
}
