package org.com.reservation.domain.interfaces.dataprovider;

import org.com.reservation.domain.entity.ReservationTicket;

import java.util.List;

public interface ReservationTicketDataProvider {
    void deleteAllByReservationIdAndSessionId(Long reservationId, Long sessionId);

    List<ReservationTicket> persistAll(List<ReservationTicket> reservationMovieTickets);
}
