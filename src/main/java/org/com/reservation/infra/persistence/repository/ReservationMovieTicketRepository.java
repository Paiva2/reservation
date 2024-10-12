package org.com.reservation.infra.persistence.repository;

import org.com.reservation.infra.persistence.entity.ReservationMovieTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationMovieTicketRepository extends JpaRepository<ReservationMovieTicketEntity, ReservationMovieTicketEntity.KeyId> {
    @Modifying
    @Query("DELETE FROM ReservationMovieTicketEntity mvt " +
        "WHERE mvt.reservation.id = :reservationId " +
        "AND mvt.reservation.session.id = :sessionId")
    void deleteAllByReservationIdAndSessionId(@Param("reservationId") Long reservationId, @Param("sessionId") Long sessionId);
}
