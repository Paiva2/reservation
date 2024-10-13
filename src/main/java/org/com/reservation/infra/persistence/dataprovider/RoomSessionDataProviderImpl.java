package org.com.reservation.infra.persistence.dataprovider;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.entity.RoomSession;
import org.com.reservation.domain.interfaces.dataprovider.RoomSessionDataProvider;
import org.com.reservation.infra.annotations.DataProvider;
import org.com.reservation.infra.persistence.entity.RoomSessionEntity;
import org.com.reservation.infra.persistence.mapper.RoomSessionMapper;
import org.com.reservation.infra.persistence.repository.RoomSessionRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@DataProvider
public class RoomSessionDataProviderImpl implements RoomSessionDataProvider {
    private final RoomSessionRepository roomSessionRepository;

    @Override
    public RoomSession persist(RoomSession roomSession) {
        RoomSessionEntity roomSessionEntity = roomSessionRepository.save(RoomSessionMapper.toRoomSessionEntity(roomSession));
        return RoomSessionMapper.toRoomSession(roomSessionEntity);
    }

    @Override
    public List<RoomSession> persistAll(List<RoomSession> roomSessions) {
        List<RoomSessionEntity> roomSessionEntities = roomSessions.stream().map(RoomSessionMapper::toRoomSessionEntity).toList();
        List<RoomSessionEntity> roomSessionEntitiesPersisted = roomSessionRepository.saveAll(roomSessionEntities);
        return roomSessionEntitiesPersisted.stream().map(RoomSessionMapper::toRoomSession).toList();
    }

    @Override
    public void deleteAllBySessionId(Long sessionId) {
        roomSessionRepository.deleteAllBySessionId(sessionId);
    }

    @Override
    public Optional<RoomSession> findBySessionAndRoom(Long sessionId, Long roomId) {
        Optional<RoomSessionEntity> roomSessionEntity = roomSessionRepository.findBySessionAndRoom(sessionId, roomId);
        if (roomSessionEntity.isEmpty()) return Optional.empty();
        return Optional.of(RoomSessionMapper.toRoomSession(roomSessionEntity.get()));
    }
}
