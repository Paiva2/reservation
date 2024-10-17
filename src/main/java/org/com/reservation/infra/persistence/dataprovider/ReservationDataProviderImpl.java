package org.com.reservation.infra.persistence.dataprovider;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.entity.Reservation;
import org.com.reservation.domain.interfaces.dataprovider.ReservationDataProvider;
import org.com.reservation.infra.persistence.entity.ReservationEntity;
import org.com.reservation.infra.persistence.mapper.ReservationMapper;
import org.com.reservation.infra.persistence.repository.ReservationRespository;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ReservationDataProviderImpl implements ReservationDataProvider {
    private final ReservationRespository reservationRespository;

    @Override
    public List<Reservation> deleteAllBySessionId(Long sessionId) {
        List<ReservationEntity> deletedReservations = reservationRespository.deleteAllBySessionId(sessionId);
        return deletedReservations.stream().map(ReservationMapper::toReservation).toList();
    }

    @Override
    public List<Reservation> findAllBySession(Long sessionId) {
        List<ReservationEntity> reservationEntities = reservationRespository.findAllBySessionId(sessionId);
        return reservationEntities.stream().map(ReservationMapper::toReservation).toList();
    }

    @Override
    public Reservation persist(Reservation reservation) {
        ReservationEntity reservationEntity = ReservationMapper.toReservationEntity(reservation);
        return ReservationMapper.toReservation(reservationRespository.save(reservationEntity));
    }
}
