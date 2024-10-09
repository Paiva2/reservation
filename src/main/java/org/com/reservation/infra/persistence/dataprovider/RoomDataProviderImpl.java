package org.com.reservation.infra.persistence.dataprovider;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.entity.Room;
import org.com.reservation.domain.interfaces.dataprovider.RoomDataProvider;
import org.com.reservation.infra.annotations.DataProvider;
import org.com.reservation.infra.persistence.entity.RoomEntity;
import org.com.reservation.infra.persistence.mapper.RoomMapper;
import org.com.reservation.infra.persistence.repository.RoomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@DataProvider
public class RoomDataProviderImpl implements RoomDataProvider {
    private final RoomRepository roomRepository;

    @Override
    public Optional<Room> findById(Long roomId) {
        Optional<RoomEntity> room = roomRepository.findById(roomId);
        if (room.isEmpty()) return Optional.empty();
        return Optional.of(RoomMapper.toRoom(room.get()));
    }

    @Override
    public List<Room> findManyById(Set<Long> ids) {
        List<RoomEntity> rooms = roomRepository.findAllById(ids);
        return rooms.stream().map(RoomMapper::toRoom).toList();
    }

    @Override
    public Page<Room> findAvailables(Pageable pageable) {
        Page<RoomEntity> roomEntities = roomRepository.findAllAvailable(pageable);
        return roomEntities.map(RoomMapper::toRoom);
    }
}
