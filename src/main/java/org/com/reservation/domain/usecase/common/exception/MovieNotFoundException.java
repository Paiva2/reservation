package org.com.reservation.domain.usecase.common.exception;

public class MovieNotFoundException extends RuntimeException {
    private final static String MESSAGE = "Movie not found!";

    public MovieNotFoundException() {
        super(MESSAGE);
    }
}
