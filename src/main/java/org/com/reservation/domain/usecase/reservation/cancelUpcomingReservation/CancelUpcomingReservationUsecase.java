package org.com.reservation.domain.usecase.reservation.cancelUpcomingReservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.com.reservation.domain.entity.Reservation;
import org.com.reservation.domain.entity.Session;
import org.com.reservation.domain.entity.User;
import org.com.reservation.domain.interfaces.dataprovider.*;
import org.com.reservation.domain.usecase.common.exception.ReservationNotFoundException;
import org.com.reservation.domain.usecase.common.exception.SessionNotActiveException;
import org.com.reservation.domain.usecase.common.exception.UserDisabledException;
import org.com.reservation.domain.usecase.common.exception.UserNotFoundException;
import org.com.reservation.domain.usecase.reservation.cancelUpcomingReservation.exception.SessionUpcomingException;

import java.util.Date;

@AllArgsConstructor
@Builder
public class CancelUpcomingReservationUsecase {
    private final UserDataProvider userDataProvider;
    private final ReservationDataProvider reservationDataProvider;
    private final SessionDataProvider sessionDataProvider;
    private final ReservationTicketDataProvider reservationTicketDataProvider;
    private final ReservationRoomSeatDataProvider reservationRoomSeatDataProvider;

    public void execute(Long userId, Long ReservationId) {
        User user = findUser(userId);
        checkUserDisabled(user);

        Reservation reservation = findReservation(ReservationId);
        checkReservationBelongToUser(user, reservation);

        Session session = reservation.getSession();
        checkSessionActive(session);
        checkSessionStarted(session);

        deleteReservationTickets(reservation.getId(), session.getId());
        deleteReservationRoomSeats(reservation.getId(), session.getId());
        deleteReservation(reservation.getId());
    }

    private User findUser(Long userId) {
        return userDataProvider.findByUserId(userId).orElseThrow(UserNotFoundException::new);
    }

    private void checkUserDisabled(User user) {
        if (user.getDisabledAt() != null) {
            throw new UserDisabledException();
        }
    }

    private Reservation findReservation(Long reservationId) {
        return reservationDataProvider.findById(reservationId).orElseThrow(ReservationNotFoundException::new);
    }

    private void checkReservationBelongToUser(User user, Reservation reservation) {
        if (!reservation.getUser().getId().equals(user.getId())) {
            throw new ReservationNotFoundException();
        }
    }

    private void checkSessionActive(Session session) {
        if (!session.isActive()) {
            throw new SessionNotActiveException(session.getId().toString());
        }
    }

    private void checkSessionStarted(Session session) {
        if (session.getStart().before(new Date())) {
            throw new SessionUpcomingException("Can only cancel reservations from upcoming sessions!");
        }
    }

    private void deleteReservationTickets(Long reservationId, Long sessionId) {
        reservationTicketDataProvider.deleteAllByReservationIdAndSessionId(reservationId, sessionId);
    }

    private void deleteReservationRoomSeats(Long reservationId, Long sessionId) {
        reservationRoomSeatDataProvider.deleteAllByReservationIdAndSessionId(reservationId, sessionId);
    }

    private void deleteReservation(Long reservationId) {
        reservationDataProvider.deleteById(reservationId);
    }
}
