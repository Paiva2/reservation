package org.com.reservation.domain.interfaces.dataprovider;

import org.com.reservation.domain.entity.Session;

public interface SessionDataProvider {
    Session persist(Session session);
}
