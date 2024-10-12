package org.com.reservation.domain.interfaces.dataprovider;

import org.com.reservation.domain.entity.RoomSession;

import java.util.List;

public interface RoomSessionDataProvider {
    RoomSession persist(RoomSession roomSession);

    List<RoomSession> persistAll(List<RoomSession> roomSessions);

    void deleteAllBySessionId(Long sessionId);
}
