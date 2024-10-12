package org.com.reservation.domain.interfaces.dataprovider;

import org.com.reservation.domain.entity.MovieGenre;

import java.util.List;

public interface MovieGenreDataProvider {
    MovieGenre persist(MovieGenre movieGenre);

    List<MovieGenre> persistAll(List<MovieGenre> movieGenres);

    void deleteAllByMovieId(Long movieId);
}
