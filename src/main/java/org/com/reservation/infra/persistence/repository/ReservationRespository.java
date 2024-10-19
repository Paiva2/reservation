package org.com.reservation.infra.persistence.repository;

import org.com.reservation.infra.persistence.entity.ReservationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRespository extends JpaRepository<ReservationEntity, Long> {
    List<ReservationEntity> deleteAllBySessionId(Long sessionId);

    @Query("SELECT rs FROM ReservationEntity rs JOIN FETCH rs.session ss WHERE ss.id = :sessionId AND ss.active = true")
    List<ReservationEntity> findAllActiveBySessionId(Long sessionId);

    @Query("SELECT rs FROM ReservationEntity rs " +
        "JOIN FETCH rs.session ss " +
        "JOIN rs.reservationRoomSeats rrs " +
        "JOIN rs.reservationTickets rt " +
        "WHERE ss.id = :sessionId ")
    Page<ReservationEntity> findAllBySessionId(Long sessionId, Pageable pageable);

    @Query(value = "SELECT * FROM tb_reservations rsv " +
        "JOIN tb_sessions ss ON ss.ss_id = rsv.res_session_id " +
        "JOIN tb_movies mv ON mv.mo_id = ss.ss_movie_id " +
        "WHERE rsv.res_user_id = :userId " +
        "AND (cast(:sessionStart as DATE) IS NULL OR date_trunc('day', ss.ss_start) = date_trunc('day', cast(:sessionStart as DATE)))", nativeQuery = true)
    Page<ReservationEntity> findAllByUserIdFetchAdditionals(Pageable pageable, Long userId, Date sessionStart);

    @Modifying
    @Query("DELETE FROM ReservationEntity WHERE id = :reservationId")
    void deleteById(@Param("reservationId") Long reservationId);
}
