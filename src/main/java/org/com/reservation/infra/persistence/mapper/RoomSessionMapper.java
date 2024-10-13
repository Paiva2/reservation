package org.com.reservation.infra.persistence.mapper;

import org.com.reservation.domain.entity.Room;
import org.com.reservation.domain.entity.RoomSeat;
import org.com.reservation.domain.entity.RoomSession;
import org.com.reservation.domain.entity.Session;
import org.com.reservation.infra.persistence.entity.RoomEntity;
import org.com.reservation.infra.persistence.entity.RoomSeatEntity;
import org.com.reservation.infra.persistence.entity.RoomSessionEntity;
import org.com.reservation.infra.persistence.entity.SessionEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class RoomSessionMapper {
    public static RoomSession toRoomSession(RoomSessionEntity roomSessionEntity) {
        if (roomSessionEntity == null) return null;

        RoomSession roomSession = new RoomSession();
        copyProperties(roomSessionEntity, roomSession);

        if (roomSessionEntity.getRoom() != null) {
            Room room = new Room();
            roomSession.setRoom(room);

            copyProperties(roomSessionEntity.getRoom(), roomSession.getRoom());

            if (roomSessionEntity.getRoom().getRoomSeats() != null) {
                List<RoomSeat> roomSeats = new ArrayList<>();

                for (RoomSeatEntity roomSeatEntity : roomSessionEntity.getRoom().getRoomSeats()) {
                    RoomSeat roomSeat = new RoomSeat();
                    copyProperties(roomSeatEntity, roomSeat);

                    roomSeats.add(roomSeat);
                }

                room.setSeats(roomSeats);
            }
        }

        if (roomSessionEntity.getSession() != null) {
            Session session = new Session();
            roomSession.setSession(session);

            copyProperties(roomSessionEntity.getSession(), roomSession.getSession());
        }

        return roomSession;
    }

    public static RoomSessionEntity toRoomSessionEntity(RoomSession roomSession) {
        if (roomSession == null) return null;

        RoomSessionEntity roomSessionEntity = new RoomSessionEntity();
        copyProperties(roomSession, roomSessionEntity);

        RoomSessionEntity.KeyId keyId = new RoomSessionEntity.KeyId();
        roomSessionEntity.setId(keyId);

        copyProperties(roomSession.getId(), roomSessionEntity.getId());

        if (roomSession.getRoom() != null) {
            RoomEntity roomEntity = new RoomEntity();
            roomSessionEntity.setRoom(roomEntity);

            copyProperties(roomSession.getRoom(), roomSessionEntity.getRoom());
        }

        if (roomSession.getSession() != null) {
            SessionEntity sessionEntity = new SessionEntity();
            roomSessionEntity.setSession(sessionEntity);

            copyProperties(roomSession.getSession(), roomSessionEntity.getSession());
        }

        return roomSessionEntity;
    }

    private static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
}
