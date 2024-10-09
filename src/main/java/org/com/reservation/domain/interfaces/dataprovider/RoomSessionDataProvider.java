package org.com.reservation.domain.interfaces.dataprovider;

import org.com.reservation.domain.entity.RoomSession;

import java.util.Date;
import java.util.List;

public interface RoomSessionDataProvider {
    List<RoomSession> findActiveByPeriodAndListOfIds(Date start, Date end, List<Long> ids);

    RoomSession persist(RoomSession roomSession);

    List<RoomSession> persistAll(List<RoomSession> roomSessions);
}
