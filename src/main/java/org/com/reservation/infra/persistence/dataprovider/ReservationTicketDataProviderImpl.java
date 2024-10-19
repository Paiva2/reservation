package org.com.reservation.infra.persistence.dataprovider;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.entity.ReservationTicket;
import org.com.reservation.domain.interfaces.dataprovider.ReservationTicketDataProvider;
import org.com.reservation.infra.persistence.entity.ReservationTicketEntity;
import org.com.reservation.infra.persistence.mapper.ReservationTicketMapper;
import org.com.reservation.infra.persistence.repository.ReservationTicketRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class ReservationTicketDataProviderImpl implements ReservationTicketDataProvider {
    private final ReservationTicketRepository reservationTicketRepository;

    @Override
    public void deleteAllByReservationIdAndSessionId(Long reservationId, Long sessionId) {
        reservationTicketRepository.deleteAllByReservationIdAndSessionId(reservationId, sessionId);
    }

    @Override
    public List<ReservationTicket> persistAll(List<ReservationTicket> reservationMovieTickets) {
        List<ReservationTicketEntity> reservationMovieTicketEntitiesConversion = reservationMovieTickets.stream().map(ReservationTicketMapper::toReservationTicketEntity).toList();
        List<ReservationTicketEntity> reservationMovieTicketEntities = reservationTicketRepository.saveAll(reservationMovieTicketEntitiesConversion);
        return reservationMovieTicketEntities.stream().map(ReservationTicketMapper::toReservationTicket).toList();
    }

    @Override
    public Optional<ReservationTicket> findById(Long reservationTicketId) {
        Optional<ReservationTicketEntity> reservationTicketEntity = reservationTicketRepository.findById(reservationTicketId);
        if (reservationTicketEntity.isEmpty()) return Optional.empty();
        return Optional.of(ReservationTicketMapper.toReservationTicket(reservationTicketEntity.get()));
    }
}
