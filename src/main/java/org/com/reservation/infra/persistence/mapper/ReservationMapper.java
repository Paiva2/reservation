package org.com.reservation.infra.persistence.mapper;

import org.com.reservation.domain.entity.Reservation;
import org.com.reservation.infra.persistence.entity.ReservationEntity;

public class ReservationMapper {
    public static Reservation toReservation(ReservationEntity reservationEntity) {
        return Reservation.builder()
            .id(reservationEntity.getId())
            .createdAt(reservationEntity.getCreatedAt())
            .updatedAt(reservationEntity.getUpdatedAt())
            .user(UserMapper.toUser(reservationEntity.getUser()))
            .session(SessionMapper.toSession(reservationEntity.getSession()))
            .reservationMovieTickets(null)
            .reservationRoomSeats(null)
            .build();
    }
}
