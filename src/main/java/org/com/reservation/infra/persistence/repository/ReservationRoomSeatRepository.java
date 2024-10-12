package org.com.reservation.infra.persistence.repository;

import org.com.reservation.infra.persistence.entity.ReservationRoomSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRoomSeatRepository extends JpaRepository<ReservationRoomSeatEntity, ReservationRoomSeatEntity.KeyId> {

    @Modifying
    @Query("DELETE FROM ReservationRoomSeatEntity rrs " +
        "WHERE rrs.reservation.id = :reservationId " +
        "AND rrs.reservation.session.id = :sessionId")
    void deleteAllByReservationIdAndSessionId(@Param("reservationId") Long reservationId, @Param("sessionId") Long sessionId);
}
