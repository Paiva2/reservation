package org.com.reservation.domain.interfaces.dataprovider;

import org.com.reservation.domain.entity.Movie;

import java.util.Optional;

public interface MovieDataProvider {
    Optional<Movie> findMovieByTitle(String name);

    Movie persist(Movie movie);

    Optional<Movie> findById(Long movieId);

    void delete(Movie movie);
}
