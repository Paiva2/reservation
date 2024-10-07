package org.com.reservation.application.config.exception;

import java.text.MessageFormat;

public class MissingHeaderException extends RuntimeException {
    private final static String MESSAGE = "Header is missing: {0}";

    public MissingHeaderException(String headerName) {
        super(MessageFormat.format(MESSAGE, headerName));
    }
}
