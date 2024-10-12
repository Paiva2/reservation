package org.com.reservation.infra.persistence.dataprovider;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.interfaces.dataprovider.ReservationMovieTicketDataProvider;
import org.com.reservation.infra.persistence.repository.ReservationMovieTicketRepository;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ReservationMovieTicketDataProviderImpl implements ReservationMovieTicketDataProvider {
    private final ReservationMovieTicketRepository reservationMovieTicketRepository;

    @Override
    public void deleteAllByReservationIdAndSessionId(Long reservationId, Long sessionId) {
        reservationMovieTicketRepository.deleteAllByReservationIdAndSessionId(reservationId, sessionId);
    }
}
