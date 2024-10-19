package org.com.reservation.domain.interfaces.dataprovider;

import org.com.reservation.domain.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationDataProvider {
    List<Reservation> deleteAllBySessionId(Long sessionId);

    List<Reservation> findAllActiveBySessionId(Long sessionId);

    Page<Reservation> findAllBySessionPageable(Long sessionId, Pageable pageable);

    Reservation persist(Reservation reservation);

    Page<Reservation> findAllByUserFetchingAdditionals(Pageable pageable, Long userId, Date sessionStart);

    Optional<Reservation> findById(Long reservationId);

    void deleteById(Long reservationId);
}
