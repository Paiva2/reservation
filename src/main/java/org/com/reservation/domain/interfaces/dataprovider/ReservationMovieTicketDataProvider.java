package org.com.reservation.domain.interfaces.dataprovider;

public interface ReservationMovieTicketDataProvider {
    void deleteAllByReservationIdAndSessionId(Long reservationId, Long sessionId);
}
