package org.com.reservation.infra.persistence.repository;

import org.com.reservation.infra.persistence.entity.MovieTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieTicketRepository extends JpaRepository<MovieTicketEntity, Long> {
    @Modifying
    void deleteByMovieId(Long movieId);

    Optional<MovieTicketEntity> findByMovieId(Long movieId);
}
