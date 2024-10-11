package org.com.reservation.domain.interfaces.dataprovider;

import org.com.reservation.domain.entity.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SessionDataProvider {
    Session persist(Session session);

    Page<Session> findAllUpcoming(Pageable pageable, Long movieId);
}
