package org.com.reservation.infra.persistence.mapper;

import org.com.reservation.domain.entity.Reservation;
import org.com.reservation.domain.entity.ReservationRoomSeat;
import org.com.reservation.infra.persistence.entity.ReservationEntity;
import org.com.reservation.infra.persistence.entity.ReservationRoomSeatEntity;
import org.com.reservation.infra.persistence.entity.RoomSeatEntity;
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

    public static ReservationRoomSeatEntity toReservationRoomSeatEntity(ReservationRoomSeat reservationRoomSeat) {
        if (reservationRoomSeat == null) return null;

        ReservationRoomSeatEntity reservationRoomSeatEntity = new ReservationRoomSeatEntity();
        copyProperties(reservationRoomSeat, reservationRoomSeatEntity);

        if (reservationRoomSeat.getReservation() != null) {
            ReservationEntity reservationEntity = new ReservationEntity();
            reservationRoomSeatEntity.setReservation(reservationEntity);
            copyProperties(reservationRoomSeat.getReservation(), reservationRoomSeatEntity.getReservation());
        }

        if (reservationRoomSeat.getRoomSeat() != null) {
            reservationRoomSeatEntity.setRoomSeat(RoomSeatMapper.toRoomSeatEntity(reservationRoomSeat.getRoomSeat()));
        }

        if (reservationRoomSeat.getId() != null) {
            ReservationRoomSeatEntity.KeyId keyId = new ReservationRoomSeatEntity.KeyId();
            reservationRoomSeatEntity.setId(keyId);
            RoomSeatEntity.KeyId reservationRoomSeatsKeyId = new RoomSeatEntity.KeyId();
            reservationRoomSeatEntity.getId().setRoomSeatIds(reservationRoomSeatsKeyId);
            copyProperties(reservationRoomSeat.getId(), reservationRoomSeatEntity.getId());
            copyProperties(reservationRoomSeat.getId().getRoomSeatIds(), reservationRoomSeatEntity.getId().getRoomSeatIds());
        }

        return reservationRoomSeatEntity;
    }

    private static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
}
