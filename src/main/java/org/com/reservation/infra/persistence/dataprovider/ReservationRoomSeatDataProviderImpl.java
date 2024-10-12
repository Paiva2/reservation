package org.com.reservation.infra.persistence.dataprovider;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.interfaces.dataprovider.ReservationRoomSeatDataProvider;
import org.com.reservation.infra.persistence.repository.ReservationRoomSeatRepository;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ReservationRoomSeatDataProviderImpl implements ReservationRoomSeatDataProvider {
    private final ReservationRoomSeatRepository reservationRoomSeatRepository;

    @Override
    public void deleteAllByReservationIdAndSessionId(Long reservationId, Long sessionId) {
        reservationRoomSeatRepository.deleteAllByReservationIdAndSessionId(reservationId, sessionId);
    }
}
