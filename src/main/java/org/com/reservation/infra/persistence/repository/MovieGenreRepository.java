package org.com.reservation.infra.persistence.repository;

import org.com.reservation.domain.entity.MovieGenre;
import org.com.reservation.infra.persistence.entity.MovieGenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieGenreRepository extends JpaRepository<MovieGenreEntity, MovieGenre.KeyId> {
}
