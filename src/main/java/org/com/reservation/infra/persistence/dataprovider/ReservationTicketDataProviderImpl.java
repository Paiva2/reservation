package org.com.reservation.infra.persistence.dataprovider;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.entity.ReservationTicket;
import org.com.reservation.domain.interfaces.dataprovider.ReservationTicketDataProvider;
import org.com.reservation.infra.persistence.entity.ReservationTicketEntity;
import org.com.reservation.infra.persistence.mapper.ReservationTicketMapper;
import org.com.reservation.infra.persistence.repository.ReservationMovieTicketRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ReservationTicketDataProviderImpl implements ReservationTicketDataProvider {
    private final ReservationMovieTicketRepository reservationMovieTicketRepository;

    @Override
    public void deleteAllByReservationIdAndSessionId(Long reservationId, Long sessionId) {
        reservationMovieTicketRepository.deleteAllByReservationIdAndSessionId(reservationId, sessionId);
    }

    @Override
    public List<ReservationTicket> persistAll(List<ReservationTicket> reservationMovieTickets) {
        List<ReservationTicketEntity> reservationMovieTicketEntitiesConversion = reservationMovieTickets.stream().map(ReservationTicketMapper::toReservationTicketEntity).toList();
        List<ReservationTicketEntity> reservationMovieTicketEntities = reservationMovieTicketRepository.saveAll(reservationMovieTicketEntitiesConversion);
        return reservationMovieTicketEntities.stream().map(ReservationTicketMapper::toReservationTicket).toList();
    }
}
