package org.com.reservation.infra.persistence.mapper;

import org.com.reservation.domain.entity.Genre;
import org.com.reservation.domain.entity.Movie;
import org.com.reservation.domain.entity.MovieGenre;
import org.com.reservation.infra.persistence.entity.GenreEntity;
import org.com.reservation.infra.persistence.entity.MovieEntity;
import org.com.reservation.infra.persistence.entity.MovieGenreEntity;
import org.springframework.beans.BeanUtils;

public class MovieGenreMapper {
    public static MovieGenre toMovieGenre(MovieGenreEntity movieGenreEntity) {
        MovieGenre movieGenre = new MovieGenre();
        copyProperties(movieGenreEntity, movieGenre);

        Genre genre = new Genre();
        movieGenre.setGenre(genre);

        Movie movie = new Movie();
        movieGenre.setMovie(movie);

        copyProperties(movieGenreEntity.getGenre(), movieGenre.getGenre());
        copyProperties(movieGenreEntity.getMovie(), movieGenre.getMovie());

        return movieGenre;
    }

    public static MovieGenreEntity toMovieGenreEntity(MovieGenre movieGenre) {
        MovieGenreEntity movieGenreEntity = new MovieGenreEntity();
        copyProperties(movieGenre, movieGenreEntity);

        if (movieGenre.getId() != null) {
            MovieGenreEntity.KeyId movieGenreKeyEntity = new MovieGenreEntity.KeyId();
            movieGenreEntity.setId(movieGenreKeyEntity);

            copyProperties(movieGenre.getId(), movieGenreEntity.getId());
        }

        if (movieGenre.getGenre() != null) {
            GenreEntity genreEntity = new GenreEntity();
            movieGenreEntity.setGenre(genreEntity);

            copyProperties(movieGenre.getGenre(), movieGenreEntity.getGenre());
        }

        if (movieGenre.getMovie() != null) {
            MovieEntity movieEntity = new MovieEntity();
            movieGenreEntity.setMovie(movieEntity);

            copyProperties(movieGenre.getMovie(), movieGenreEntity.getMovie());
        }

        return movieGenreEntity;
    }

    private static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
}
