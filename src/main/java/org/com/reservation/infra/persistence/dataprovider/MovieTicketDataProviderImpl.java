package org.com.reservation.infra.persistence.dataprovider;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.entity.MovieTicket;
import org.com.reservation.domain.interfaces.dataprovider.MovieTicketDataProvider;
import org.com.reservation.infra.annotations.DataProvider;
import org.com.reservation.infra.persistence.entity.MovieTicketEntity;
import org.com.reservation.infra.persistence.mapper.MovieTicketMapper;
import org.com.reservation.infra.persistence.repository.MovieTicketRepository;

import java.util.Optional;

@AllArgsConstructor
@DataProvider
public class MovieTicketDataProviderImpl implements MovieTicketDataProvider {
    private final MovieTicketRepository movieTicketRepository;

    @Override
    public MovieTicket persist(MovieTicket movieTicket) {
        MovieTicketEntity movieTicketPersisted = movieTicketRepository.save(MovieTicketMapper.toMovieTicketEntity(movieTicket));
        return MovieTicketMapper.toMovieTicket(movieTicketPersisted);
    }

    @Override
    public void deleteByMovie(Long movieId) {
        movieTicketRepository.deleteByMovieId(movieId);
    }

    @Override
    public Optional<MovieTicket> findByMovieId(Long movieId) {
        Optional<MovieTicketEntity> movieTicketEntity = movieTicketRepository.findByMovieId(movieId);
        if (movieTicketEntity.isEmpty()) return Optional.empty();
        return Optional.of(MovieTicketMapper.toMovieTicket(movieTicketEntity.get()));
    }
}
