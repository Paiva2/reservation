package org.com.reservation.infra.persistence.dataprovider;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.entity.RoomSeat;
import org.com.reservation.domain.interfaces.dataprovider.RoomsSeatsDataProvider;
import org.com.reservation.infra.annotations.DataProvider;
import org.com.reservation.infra.persistence.entity.RoomSeatEntity;
import org.com.reservation.infra.persistence.mapper.RoomSeatMapper;
import org.com.reservation.infra.persistence.repository.RoomsSeatsRepository;

import java.util.List;

@AllArgsConstructor
@DataProvider
public class RoomsSeatsDataProviderImpl implements RoomsSeatsDataProvider {
    private final RoomsSeatsRepository roomsSeatsRepository;

    @Override
    public List<RoomSeat> findAllByRoomSorted(Long roomId) {
        List<RoomSeatEntity> roomSeatEntities = roomsSeatsRepository.findAllByRoomSorted(roomId);
        return roomSeatEntities.stream().map(RoomSeatMapper::toRoomSeat).toList();
    }
}
