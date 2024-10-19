package org.com.reservation.domain.interfaces.dataprovider;

import org.com.reservation.domain.entity.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SessionDataProvider {
    Session persist(Session session);

    Page<Session> findAllUpcoming(Pageable pageable, Long movieId);

    List<Session> deleteAllByMovie(Long movieId);

    List<Session> findByMovieId(Long movieId);

    List<Session> findActiveSessionsByPeriodAndRooms(Date start, List<Long> roomIds);

    Optional<Session> findActiveById(Long id);

    Optional<Session> findByReservationId(Long reservationId);
}
