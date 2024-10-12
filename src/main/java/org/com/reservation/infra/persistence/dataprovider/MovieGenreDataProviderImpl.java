package org.com.reservation.infra.persistence.dataprovider;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.entity.MovieGenre;
import org.com.reservation.domain.interfaces.dataprovider.MovieGenreDataProvider;
import org.com.reservation.infra.annotations.DataProvider;
import org.com.reservation.infra.persistence.entity.MovieGenreEntity;
import org.com.reservation.infra.persistence.mapper.MovieGenreMapper;
import org.com.reservation.infra.persistence.repository.MovieGenreRepository;

import java.util.List;

@AllArgsConstructor
@DataProvider
public class MovieGenreDataProviderImpl implements MovieGenreDataProvider {
    private final MovieGenreRepository movieGenreRepository;

    @Override
    public MovieGenre persist(MovieGenre movieGenre) {
        MovieGenreEntity moviePersisted = movieGenreRepository.save(MovieGenreMapper.toMovieGenreEntity(movieGenre));
        return MovieGenreMapper.toMovieGenre(moviePersisted);
    }

    @Override
    public List<MovieGenre> persistAll(List<MovieGenre> movieGenres) {
        List<MovieGenreEntity> movieGenreEntities = movieGenreRepository.saveAll(movieGenres.stream().map(MovieGenreMapper::toMovieGenreEntity).toList());
        return movieGenreEntities.stream().map(MovieGenreMapper::toMovieGenre).toList();
    }

    @Override
    public void deleteAllByMovieId(Long movieId) {
        movieGenreRepository.deleteAllByMovieId(movieId);
    }
}
