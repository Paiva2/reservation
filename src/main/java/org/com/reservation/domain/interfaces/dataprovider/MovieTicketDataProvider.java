package org.com.reservation.domain.interfaces.dataprovider;

import org.com.reservation.domain.entity.MovieTicket;

import java.util.Optional;

public interface MovieTicketDataProvider {
    MovieTicket persist(MovieTicket movieTicket);

    void deleteByMovie(Long movieId);

    Optional<MovieTicket> findByMovieId(Long movieId);
}
