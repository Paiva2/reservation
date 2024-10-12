package org.com.reservation.domain.interfaces.dataprovider;

public interface ReservationRoomSeatDataProvider {
    void deleteAllByReservationIdAndSessionId(Long reservationId, Long sessionId);
}
