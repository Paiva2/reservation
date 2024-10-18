package org.com.reservation.domain.usecase.common.exception;

import org.com.reservation.domain.common.exception.ForbiddenException;

public class InvalidPermissionsException extends ForbiddenException {
    private final static String MESSAGE = "User has no necessary permissions to access resource.";

    public InvalidPermissionsException() {
        super(MESSAGE);
    }
}
