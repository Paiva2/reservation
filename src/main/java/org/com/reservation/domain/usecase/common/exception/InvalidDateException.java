package org.com.reservation.domain.usecase.common.exception;

import org.com.reservation.domain.common.exception.BadRequestException;

public class InvalidDateException extends BadRequestException {
    public InvalidDateException(String message) {
        super(message);
    }
}
