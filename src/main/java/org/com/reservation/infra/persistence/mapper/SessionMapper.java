package org.com.reservation.infra.persistence.mapper;

import org.com.reservation.domain.entity.Movie;
import org.com.reservation.domain.entity.Session;
import org.com.reservation.infra.persistence.entity.MovieEntity;
import org.com.reservation.infra.persistence.entity.SessionEntity;
import org.springframework.beans.BeanUtils;

public class SessionMapper {
    public static Session toSession(SessionEntity sessionEntity) {
        if (sessionEntity == null) return null;
        Session session = new Session();
        copyProperties(sessionEntity, session);

        if (sessionEntity.getMovie() != null) {
            Movie movie = new Movie();
            session.setMovie(movie);

            copyProperties(sessionEntity.getMovie(), session.getMovie());
        }

        return session;
    }

    public static SessionEntity toSessionEntity(Session session) {
        if (session == null) return null;
        SessionEntity sessionEntity = new SessionEntity();
        copyProperties(session, sessionEntity);

        if (session.getMovie() != null) {
            MovieEntity movieEntity = new MovieEntity();
            sessionEntity.setMovie(movieEntity);

            copyProperties(session.getMovie(), sessionEntity.getMovie());
        }

        return sessionEntity;
    }

    private static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
}
