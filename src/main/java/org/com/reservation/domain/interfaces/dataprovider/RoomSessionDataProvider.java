package org.com.reservation.domain.interfaces.dataprovider;

import org.com.reservation.domain.entity.RoomSession;

import java.util.List;
import java.util.Optional;

public interface RoomSessionDataProvider {
    RoomSession persist(RoomSession roomSession);

    List<RoomSession> persistAll(List<RoomSession> roomSessions);

    void deleteAllBySessionId(Long sessionId);

    Optional<RoomSession> findBySessionAndRoom(Long sessionId, Long roomId);
}
