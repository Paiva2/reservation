package org.com.reservation.infra.persistence.dataprovider;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.entity.ReservationRoomSeat;
import org.com.reservation.domain.interfaces.dataprovider.ReservationRoomSeatDataProvider;
import org.com.reservation.infra.persistence.entity.ReservationRoomSeatEntity;
import org.com.reservation.infra.persistence.mapper.ReservationRoomSeatMapper;
import org.com.reservation.infra.persistence.repository.ReservationRoomSeatRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ReservationRoomSeatDataProviderImpl implements ReservationRoomSeatDataProvider {
    private final ReservationRoomSeatRepository reservationRoomSeatRepository;

    @Override
    public void deleteAllByReservationIdAndSessionId(Long reservationId, Long sessionId) {
        reservationRoomSeatRepository.deleteAllByReservationIdAndSessionId(reservationId, sessionId);
    }

    @Override
    public List<ReservationRoomSeat> findByReservationAndRoom(Long reservationId, Long roomId) {
        List<ReservationRoomSeatEntity> reservationRoomSeatsEntities = reservationRoomSeatRepository.findByReservationAndRoom(reservationId, roomId);
        return reservationRoomSeatsEntities.stream().map(ReservationRoomSeatMapper::toReservationRoomSeat).toList();
    }

    @Override
    public List<ReservationRoomSeat> findManyBySessionRoomSeat(Long sessionId, Long roomId, List<Long> seatsIds) {
        List<ReservationRoomSeatEntity> reservationRoomSeatsEntities = reservationRoomSeatRepository.findManyBySessionIdRoomIdSeatsIds(sessionId, roomId, seatsIds);
        return reservationRoomSeatsEntities.stream().map(ReservationRoomSeatMapper::toReservationRoomSeat).toList();
    }

    @Override
    public List<ReservationRoomSeat> persistAll(List<ReservationRoomSeat> reservationRoomSeats) {
        List<ReservationRoomSeatEntity> reservationEntitiesConverted = reservationRoomSeats.stream().map(ReservationRoomSeatMapper::toReservationRoomSeatEntity).toList();
        List<ReservationRoomSeatEntity> reservationRoomSeatsEntities = reservationRoomSeatRepository.saveAll(reservationEntitiesConverted);
        return reservationRoomSeatsEntities.stream().map(ReservationRoomSeatMapper::toReservationRoomSeat).toList();
    }
}
