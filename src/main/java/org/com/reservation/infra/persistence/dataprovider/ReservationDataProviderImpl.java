package org.com.reservation.infra.persistence.dataprovider;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.entity.Reservation;
import org.com.reservation.domain.interfaces.dataprovider.ReservationDataProvider;
import org.com.reservation.infra.persistence.entity.ReservationEntity;
import org.com.reservation.infra.persistence.mapper.ReservationMapper;
import org.com.reservation.infra.persistence.repository.ReservationRespository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Page<Reservation> findAllByUserFetchingAdditionals(Pageable pageable, Long userId, Date sessionStart) {
        Page<ReservationEntity> reservationsPage = reservationRespository.findAllByUserIdFetchAdditionals(pageable, userId, sessionStart);
        return reservationsPage.map(ReservationMapper::toReservation);
    }

    @Override
    public Optional<Reservation> findById(Long reservationId) {
        Optional<ReservationEntity> reservationEntity = reservationRespository.findById(reservationId);
        if (reservationEntity.isEmpty()) return Optional.empty();
        return Optional.of(ReservationMapper.toReservation(reservationEntity.get()));
    }

    @Override
    public void deleteById(Long reservationId) {
        reservationRespository.deleteById(reservationId);
    }
}
