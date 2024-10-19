package org.com.reservation.infra.persistence.repository;

import org.com.reservation.infra.persistence.entity.ReservationTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationTicketRepository extends JpaRepository<ReservationTicketEntity, Long> {
    @Modifying
    @Query("DELETE FROM ReservationTicketEntity rt " +
        "WHERE rt.reservation.id = :reservationId " +
        "AND rt.reservation.session.id = :sessionId")
    void deleteAllByReservationIdAndSessionId(@Param("reservationId") Long reservationId, @Param("sessionId") Long sessionId);
}
