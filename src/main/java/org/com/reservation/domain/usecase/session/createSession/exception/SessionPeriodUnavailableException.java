package org.com.reservation.domain.usecase.session.createSession.exception;

import java.text.MessageFormat;

public class SessionPeriodUnavailableException extends RuntimeException {
    public static final String MESSAGE = "Rooms {0} already being used by other Session on this period! Sessions: {1}";
    public static final String MESSAGE_MANY = "There are rooms being used on this period by other session: {0}";

    public SessionPeriodUnavailableException(String roomsSessions) {
        super(MessageFormat.format(MESSAGE_MANY, roomsSessions));
    }

    public SessionPeriodUnavailableException(Long rooom, Long session) {
        super(MessageFormat.format(MESSAGE, rooom, session));
    }
}
