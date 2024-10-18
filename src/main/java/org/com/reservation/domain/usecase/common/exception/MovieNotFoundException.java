package org.com.reservation.domain.usecase.common.exception;

import org.com.reservation.domain.common.exception.NotFoundException;

public class MovieNotFoundException extends NotFoundException {
    private final static String MESSAGE = "Movie not found!";

    public MovieNotFoundException() {
        super(MESSAGE);
    }
}
