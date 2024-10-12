package org.com.reservation.infra.persistence.dataprovider;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.entity.Movie;
import org.com.reservation.domain.interfaces.dataprovider.MovieDataProvider;
import org.com.reservation.infra.annotations.DataProvider;
import org.com.reservation.infra.persistence.entity.MovieEntity;
import org.com.reservation.infra.persistence.mapper.MovieMapper;
import org.com.reservation.infra.persistence.repository.MovieRepository;

import java.util.Optional;

@AllArgsConstructor
@DataProvider
public class MovieDataProviderImpl implements MovieDataProvider {
    private final MovieRepository movieRepository;

    @Override
    public Optional<Movie> findMovieByTitle(String name) {
        Optional<MovieEntity> movieEntity = movieRepository.findByTitle(name);
        if (!movieEntity.isPresent()) return Optional.empty();
        return Optional.of(MovieMapper.toMovie(movieEntity.get()));
    }

    @Override
    public Movie persist(Movie movie) {
        MovieEntity movieEntity = MovieMapper.toMovieEntity(movie);
        MovieEntity moviePersisted = movieRepository.save(movieEntity);
        return MovieMapper.toMovie(moviePersisted);
    }

    @Override
    public Optional<Movie> findById(Long movieId) {
        Optional<MovieEntity> movieEntity = movieRepository.findById(movieId);
        if (!movieEntity.isPresent()) return Optional.empty();
        return Optional.of(MovieMapper.toMovie(movieEntity.get()));
    }

    @Override
    public void delete(Movie movie) {
        movieRepository.deleteById(movie.getId());
    }
}
