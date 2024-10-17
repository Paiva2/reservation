package org.com.reservation.domain.interfaces.dataprovider;

import org.com.reservation.domain.entity.Reservation;

import java.util.List;

public interface ReservationDataProvider {
    List<Reservation> deleteAllBySessionId(Long sessionId);

    List<Reservation> findAllBySession(Long sessionId);

    Reservation persist(Reservation reservation);
}
