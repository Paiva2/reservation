package org.com.reservation.infra.persistence.mapper;

import org.com.reservation.domain.entity.Movie;
import org.com.reservation.infra.persistence.entity.MovieEntity;
import org.springframework.beans.BeanUtils;

public class MovieMapper {
    public static Movie toMovie(MovieEntity entity) {
        if (entity == null) return null;

        Movie movie = new Movie();
        copyProperties(entity, movie);

        return movie;
    }

    public static MovieEntity toMovieEntity(Movie movie) {
        if (movie == null) return null;

        MovieEntity movieEntity = new MovieEntity();
        BeanUtils.copyProperties(movie, movieEntity);

        return movieEntity;
    }

    private static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
}
