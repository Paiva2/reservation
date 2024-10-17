package org.com.reservation.domain.usecase.common.exception;

import java.text.MessageFormat;

public class InvalidPropertyException extends RuntimeException {
    public final static String MESSAGE = "Invalid property format! {0}. Valid formats are: {1}";

    public InvalidPropertyException(String property, String validProperties) {
        super(MessageFormat.format(MESSAGE, property, validProperties));
    }
}
