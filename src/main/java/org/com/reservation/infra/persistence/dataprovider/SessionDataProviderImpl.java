package org.com.reservation.infra.persistence.dataprovider;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.entity.Session;
import org.com.reservation.domain.interfaces.dataprovider.SessionDataProvider;
import org.com.reservation.infra.annotations.DataProvider;
import org.com.reservation.infra.persistence.entity.SessionEntity;
import org.com.reservation.infra.persistence.mapper.SessionMapper;
import org.com.reservation.infra.persistence.repository.SessionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor
@DataProvider
public class SessionDataProviderImpl implements SessionDataProvider {
    private final SessionRepository sessionRepository;

    @Override
    public Session persist(Session session) {
        SessionEntity persistedSession = sessionRepository.save(SessionMapper.toSessionEntity(session));
        return SessionMapper.toSession(persistedSession);
    }

    @Override
    public Page<Session> findAllUpcoming(Pageable pageable, Long movieId) {
        Page<SessionEntity> sessionPage = sessionRepository.findAllUpcoming(movieId, pageable);
        return sessionPage.map(SessionMapper::toSession);
    }
}
