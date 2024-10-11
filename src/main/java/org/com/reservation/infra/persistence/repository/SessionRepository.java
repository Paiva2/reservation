package org.com.reservation.infra.persistence.repository;

import org.com.reservation.infra.persistence.entity.SessionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Long> {
    @Query(value = "SELECT DISTINCT * FROM tb_sessions ss " +
        "JOIN tb_movies mv ON mv.mo_id = ss.ss_movie_id " +
        "WHERE ss.ss_active IS TRUE " +
        "AND date_trunc('day', ss.ss_start) >= date_trunc('day', current_date) " +
        "AND (:movieId IS NULL OR ss.ss_movie_id = :movieId) ", nativeQuery = true)
    Page<SessionEntity> findAllUpcoming(@Param("movieId") Long movieId, Pageable pageable);
}
