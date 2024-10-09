package org.com.reservation.infra.persistence.mapper;

import org.com.reservation.domain.entity.Room;
import org.com.reservation.infra.persistence.entity.RoomEntity;
import org.springframework.beans.BeanUtils;

public class RoomMapper {
    public static Room toRoom(RoomEntity roomEntity) {
        if (roomEntity == null) return null;
        Room room = new Room();
        copyProperties(roomEntity, room);
        
        return room;
    }

    private static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
}
