package org.com.reservation.domain.usecase.common.exception;

import java.text.MessageFormat;

public class SessionNotActiveException extends RuntimeException {
    private static final String MESSAGE = "Session with id {0} is not active.";

    public SessionNotActiveException(String sessionId) {
        super(MessageFormat.format(MESSAGE, sessionId));
    }
}
