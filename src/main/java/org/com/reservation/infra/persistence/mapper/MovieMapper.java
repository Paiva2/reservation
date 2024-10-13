package org.com.reservation.infra.persistence.mapper;

import org.com.reservation.domain.entity.Movie;
import org.com.reservation.domain.entity.MovieGenre;
import org.com.reservation.domain.entity.MovieTicket;
import org.com.reservation.domain.entity.Session;
import org.com.reservation.infra.persistence.entity.MovieEntity;
import org.com.reservation.infra.persistence.entity.MovieGenreEntity;
import org.com.reservation.infra.persistence.entity.SessionEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class MovieMapper {
    public static Movie toMovie(MovieEntity entity) {
        if (entity == null) return null;

        Movie movie = new Movie();
        copyProperties(entity, movie);

        if (entity.getMovieGenres() != null) {
            List<MovieGenre> movieGenres = new ArrayList<>();

            for (MovieGenreEntity movieGenreEntity : entity.getMovieGenres()) {
                movieGenres.add(MovieGenreMapper.toMovieGenre(movieGenreEntity));
            }

            movie.setMovieGenres(movieGenres);
        }

        if (entity.getSessions() != null) {
            List<Session> sessions = new ArrayList<>();

            for (SessionEntity sessionEntity : entity.getSessions()) {
                sessions.add(SessionMapper.toSession(sessionEntity));
            }

            movie.setSessions(sessions);
        }

        if (entity.getMovieTicket() != null) {
            MovieTicket movieTicket = new MovieTicket();
            movie.setMovieTicket(movieTicket);

            copyProperties(entity.getMovieTicket(), movie.getMovieTicket());
        }

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
