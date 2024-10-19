package org.com.reservation.infra.persistence.repository;

import org.com.reservation.infra.persistence.entity.SessionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Long> {
    @Query(value = """ 
            SELECT * FROM tb_sessions ss
            JOIN tb_movies mv ON mv.mo_id = ss.ss_movie_id
            WHERE ss.ss_active IS TRUE
            AND date_trunc('day', ss.ss_start) >= date_trunc('day', current_date)
            AND (:movieId IS NULL OR ss.ss_movie_id = :movieId)
        """, nativeQuery = true)
    Page<SessionEntity> findAllUpcoming(@Param("movieId") Long movieId, Pageable pageable);

    @Modifying
    @Query(value = "DELETE FROM tb_sessions ss WHERE ss.ss_movie_id = :movieId RETURNING *", nativeQuery = true)
    List<SessionEntity> deleteAllByMovieId(@Param("movieId") Long movieId);

    @Query("SELECT ss FROM SessionEntity ss " +
        "JOIN FETCH ss.roomSessions rs " +
        "WHERE rs.room.id IN (:roomIds) " +
        "AND ss.active IS TRUE " +
        "AND ss.end >= :start")
    List<SessionEntity> findActiveByPeriodAndListOfRoomIds(@Param("start") Date start, @Param("roomIds") List<Long> roomIds);

    List<SessionEntity> findAllByMovieId(Long movieId);

    @Query("SELECT ss FROM SessionEntity ss JOIN FETCH ss.movie mo WHERE ss.id = :id AND ss.active = TRUE")
    Optional<SessionEntity> findActiveById(@Param("id") Long id);

    @Query("SELECT ss FROM SessionEntity ss JOIN ss.reservations rs WHERE rs.id = :reservationId")
    Optional<SessionEntity> findByReservationId(@Param("reservationId") Long reservationId);
}
