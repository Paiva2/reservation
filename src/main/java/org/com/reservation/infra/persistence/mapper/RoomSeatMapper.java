package org.com.reservation.infra.persistence.mapper;

import org.com.reservation.domain.entity.Room;
import org.com.reservation.domain.entity.RoomSeat;
import org.com.reservation.domain.entity.Seat;
import org.com.reservation.infra.persistence.entity.RoomEntity;
import org.com.reservation.infra.persistence.entity.RoomSeatEntity;
import org.com.reservation.infra.persistence.entity.SeatEntity;
import org.springframework.beans.BeanUtils;


public class RoomSeatMapper {
    public static RoomSeat toRoomSeat(RoomSeatEntity roomSeatEntity) {
        if (roomSeatEntity == null) return null;

        RoomSeat roomSeat = new RoomSeat();
        copyProperties(roomSeatEntity, roomSeat);

        if (roomSeatEntity.getSeat() != null) {
            Seat seat = new Seat();
            roomSeat.setSeat(seat);
            copyProperties(roomSeatEntity.getSeat(), roomSeat.getSeat());
        }

        if (roomSeatEntity.getRoom() != null) {
            Room room = new Room();
            roomSeat.setRoom(room);
            copyProperties(roomSeatEntity.getRoom(), roomSeat.getRoom());
        }

        if (roomSeatEntity.getId() != null) {
            RoomSeat.KeyId keyId = new RoomSeat.KeyId();
            roomSeat.setId(keyId);
            copyProperties(roomSeatEntity.getId(), roomSeat.getId());
        }

        return roomSeat;
    }

    public static RoomSeatEntity toRoomSeatEntity(RoomSeat roomSeat) {
        if (roomSeat == null) return null;

        RoomSeatEntity roomSeatEntity = new RoomSeatEntity();
        copyProperties(roomSeat, roomSeatEntity);

        if (roomSeat.getSeat() != null) {
            SeatEntity seatEntity = new SeatEntity();
            roomSeatEntity.setSeat(seatEntity);
            copyProperties(roomSeat.getSeat(), roomSeatEntity.getSeat());
        }

        if (roomSeat.getRoom() != null) {
            RoomEntity roomEntity = new RoomEntity();
            roomSeatEntity.setRoom(roomEntity);
            copyProperties(roomSeat.getRoom(), roomSeatEntity.getRoom());
        }

        if (roomSeat.getId() != null) {
            RoomSeatEntity.KeyId keyId = new RoomSeatEntity.KeyId();
            roomSeatEntity.setId(keyId);
            copyProperties(roomSeat.getId(), roomSeatEntity.getId());
        }

        return roomSeatEntity;
    }

    private static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
}
