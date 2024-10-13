package org.com.reservation.infra.persistence.mapper;

import org.com.reservation.domain.entity.Reservation;
import org.com.reservation.domain.entity.ReservationRoomSeat;
import org.com.reservation.infra.persistence.entity.ReservationRoomSeatEntity;
import org.springframework.beans.BeanUtils;

public class ReservationRoomSeatMapper {
    public static ReservationRoomSeat toReservationRoomSeat(ReservationRoomSeatEntity reservationRoomSeatEntity) {
        if (reservationRoomSeatEntity == null) return null;

        ReservationRoomSeat reservationRoomSeat = new ReservationRoomSeat();
        copyProperties(reservationRoomSeatEntity, reservationRoomSeat);

        if (reservationRoomSeatEntity.getReservation() != null) {
            Reservation reservation = new Reservation();
            reservationRoomSeat.setReservation(reservation);
            copyProperties(reservationRoomSeatEntity.getReservation(), reservationRoomSeat.getReservation());
        }

        if (reservationRoomSeatEntity.getRoomSeat() != null) {
            reservationRoomSeat.setRoomSeat(RoomSeatMapper.toRoomSeat(reservationRoomSeatEntity.getRoomSeat()));
        }

        return reservationRoomSeat;
    }

    private static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
}
