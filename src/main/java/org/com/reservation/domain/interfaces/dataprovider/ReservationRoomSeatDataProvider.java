package org.com.reservation.domain.interfaces.dataprovider;

import org.com.reservation.domain.entity.ReservationRoomSeat;

import java.util.List;

public interface ReservationRoomSeatDataProvider {
    void deleteAllByReservationIdAndSessionId(Long reservationId, Long sessionId);

    List<ReservationRoomSeat> findByReservationAndRoom(Long reservationId, Long roomId);
}
