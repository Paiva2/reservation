package org.com.reservation.domain.usecase.common.exception;

import org.com.reservation.domain.common.exception.BadRequestException;

import java.text.MessageFormat;

public class SessionNotActiveException extends BadRequestException {
    private static final String MESSAGE = "Session with id {0} is not active.";

    public SessionNotActiveException(String sessionId) {
        super(MessageFormat.format(MESSAGE, sessionId));
    }
}
