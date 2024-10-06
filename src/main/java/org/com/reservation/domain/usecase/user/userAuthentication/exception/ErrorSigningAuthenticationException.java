package org.com.reservation.domain.usecase.user.userAuthentication.exception;

import java.text.MessageFormat;

public class ErrorSigningAuthenticationException extends RuntimeException {
    private final static String MESSAGE = "There was an error while signing a new authentication token. {0}";

    public ErrorSigningAuthenticationException(String err) {
        super(MessageFormat.format(MESSAGE, err));
    }
}
