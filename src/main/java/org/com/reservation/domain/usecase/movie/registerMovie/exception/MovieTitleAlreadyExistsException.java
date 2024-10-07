package org.com.reservation.domain.usecase.movie.registerMovie.exception;

import java.text.MessageFormat;

public class MovieTitleAlreadyExistsException extends RuntimeException {
    private final static String MESSAGE = "Movie title {0} already exists";

    public MovieTitleAlreadyExistsException(String movieTitle) {
        super(MessageFormat.format(MESSAGE, movieTitle));
    }
}
