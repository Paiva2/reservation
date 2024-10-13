package org.com.reservation.infra.persistence.mapper;

import org.com.reservation.domain.entity.Room;
import org.com.reservation.domain.entity.RoomSeat;
import org.com.reservation.domain.entity.Seat;
import org.com.reservation.infra.persistence.entity.RoomSeatEntity;
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
        
        return roomSeat;
    }

    private static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
}
