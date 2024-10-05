package org.com.reservation.infra.persistence.mapper;

import org.com.reservation.domain.entity.Reservation;
import org.com.reservation.domain.entity.Session;
import org.com.reservation.infra.persistence.entity.SessionEntity;

import java.util.List;

public class SessionMapper {
    public static Session toSession(SessionEntity sessionEntity) {
        List<Reservation> reservations;

        if (sessionEntity.getReservations().isEmpty()) {
            reservations = null;
        } else {
            reservations = sessionEntity.getReservations().stream().map(ReservationMapper::toReservation).toList();
        }

        return Session.builder()
            .id(sessionEntity.getId())
            .start(sessionEntity.getStart())
            .end(sessionEntity.getEnd())
            .createdAt(sessionEntity.getCreatedAt())
            .updatedAt(sessionEntity.getUpdatedAt())
            .reservations(reservations)
            .roomSessions(null)
            .movie(null)
            .build();
    }
}
