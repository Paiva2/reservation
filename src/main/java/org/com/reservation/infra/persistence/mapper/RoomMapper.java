package org.com.reservation.infra.persistence.mapper;

import org.com.reservation.domain.entity.Room;
import org.com.reservation.domain.entity.RoomSeat;
import org.com.reservation.domain.entity.Seat;
import org.com.reservation.infra.persistence.entity.RoomEntity;
import org.com.reservation.infra.persistence.entity.RoomSeatEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class RoomMapper {
    public static Room toRoom(RoomEntity roomEntity) {
        if (roomEntity == null) return null;
        Room room = new Room();
        copyProperties(roomEntity, room);

        if (roomEntity.getRoomSeats() != null) {
            List<RoomSeat> roomSeats = new ArrayList<>();

            for (RoomSeatEntity roomSeatEntity : roomEntity.getRoomSeats()) {
                RoomSeat roomSeat = new RoomSeat();
                Room roomCopy = new Room();
                Seat seat = new Seat();

                roomSeat.setRoom(roomCopy);
                roomSeat.setSeat(seat);

                copyProperties(roomSeatEntity, roomSeat);
                copyProperties(roomSeatEntity.getSeat(), roomSeat.getSeat());
                copyProperties(roomSeatEntity.getRoom(), roomSeat.getRoom());

                roomSeats.add(roomSeat);
            }

            room.setSeats(roomSeats);
        }

        return room;
    }

    public static Room toRoomCountingSeats(RoomEntity roomEntity) {
        if (roomEntity == null) return null;
        Room room = new Room();
        copyProperties(roomEntity, room);

        if (roomEntity.getRoomSeats() != null) {
            room.setTotalSeats(Long.valueOf(roomEntity.getRoomSeats().size()));
        }

        return room;
    }

    private static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
}
