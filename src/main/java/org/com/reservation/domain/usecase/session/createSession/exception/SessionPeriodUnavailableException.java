package org.com.reservation.domain.usecase.session.createSession.exception;

import java.text.MessageFormat;
import java.util.List;

public class SessionPeriodUnavailableException extends RuntimeException {
    public static final String MESSAGE = "Room {0} already being used by other Session on this period! Session: {1}";

    public SessionPeriodUnavailableException(List<Long> rooms, List<Long> sessions) {
        super(MessageFormat.format(MESSAGE, rooms, sessions));
    }

    public SessionPeriodUnavailableException(Long rooom, Long session) {
        super(MessageFormat.format(MESSAGE, rooom, session));
    }
}
