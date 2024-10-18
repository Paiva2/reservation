package org.com.reservation.domain.usecase.movie.registerMovie.exception;

import org.com.reservation.domain.common.exception.NotFoundException;

import java.text.MessageFormat;
import java.util.List;

public class GenresNotFoundException extends NotFoundException {
    public static final String MESSAGE = "Genres not found: {0}";

    public GenresNotFoundException(List<Long> list) {
        super(MessageFormat.format(MESSAGE, list.toString()));
    }
}
