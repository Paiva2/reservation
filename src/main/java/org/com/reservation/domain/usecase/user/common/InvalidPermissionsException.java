package org.com.reservation.domain.usecase.user.common;

public class InvalidPermissionsException extends RuntimeException {
    private final static String MESSAGE = "User has no necessary permissions to access resource.";

    public InvalidPermissionsException() {
        super(MESSAGE);
    }
}
