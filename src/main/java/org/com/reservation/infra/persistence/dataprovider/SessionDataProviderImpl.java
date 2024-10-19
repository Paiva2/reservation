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

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Session> deleteAllByMovie(Long movieId) {
        List<SessionEntity> deletedSessions = sessionRepository.deleteAllByMovieId(movieId);
        return deletedSessions.stream().map(SessionMapper::toSession).toList();
    }

    @Override
    public List<Session> findByMovieId(Long movieId) {
        List<SessionEntity> sessionEntities = sessionRepository.findAllByMovieId(movieId);
        return sessionEntities.stream().map(SessionMapper::toSession).toList();
    }

    public List<Session> findActiveSessionsByPeriodAndRooms(Date start, List<Long> roomIds) {
        List<SessionEntity> sessionEntities = sessionRepository.findActiveByPeriodAndListOfRoomIds(start, roomIds);
        return sessionEntities.stream().map(SessionMapper::toSession).toList();
    }

    @Override
    public Optional<Session> findActiveById(Long id) {
        Optional<SessionEntity> sessionEntity = sessionRepository.findActiveById(id);
        if (sessionEntity.isEmpty()) return Optional.empty();
        return Optional.of(SessionMapper.toSession(sessionEntity.get()));
    }

    @Override
    public Optional<Session> findByReservationId(Long reservationId) {
        Optional<SessionEntity> sessionEntity = sessionRepository.findByReservationId(reservationId);
        if (sessionEntity.isEmpty()) return Optional.empty();
        return Optional.of(SessionMapper.toSession(sessionEntity.get()));
    }
}
