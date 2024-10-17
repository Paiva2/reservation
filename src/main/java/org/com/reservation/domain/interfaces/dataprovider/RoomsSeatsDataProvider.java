package org.com.reservation.domain.interfaces.dataprovider;

import org.com.reservation.domain.entity.RoomSeat;

import java.util.List;

public interface RoomsSeatsDataProvider {
    List<RoomSeat> findAllByRoomSorted(Long roomId);

    List<RoomSeat> findManyByIdAndRoom(Long roomId, List<Long> roomSeatsIds);
}
