package org.com.reservation.domain.interfaces.dataprovider;

import org.com.reservation.domain.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.Optional;

public interface MovieDataProvider {
    Optional<Movie> findMovieByTitle(String name);

    Movie persist(Movie movie);

    Optional<Movie> findById(Long movieId);

    void delete(Movie movie);

    Page<Movie> findAllPageable(Pageable pageable, String title, String genre, Date date);
}
