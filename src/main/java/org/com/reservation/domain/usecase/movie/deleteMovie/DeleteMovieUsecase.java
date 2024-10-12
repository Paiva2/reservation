package org.com.reservation.domain.usecase.movie.deleteMovie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.com.reservation.domain.entity.*;
import org.com.reservation.domain.enumeration.EnumRole;
import org.com.reservation.domain.interfaces.dataprovider.*;
import org.com.reservation.domain.usecase.common.exception.MovieNotFoundException;
import org.com.reservation.domain.usecase.user.common.InvalidPermissionsException;
import org.com.reservation.domain.usecase.user.common.exception.UserNotFoundException;
import org.com.reservation.domain.usecase.user.userAuthentication.exception.UserDisabledException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

//TODO: testar deletar com reservation, reservation movietickets, reservation room seat
@AllArgsConstructor
@Builder
public class DeleteMovieUsecase {
    private final static List<EnumRole> necessaryRoles = List.of(EnumRole.ADMIN);

    private final UserDataProvider userDataProvider;
    private final UserRoleDataProvider userRoleDataProvider;
    private final RoomSessionDataProvider roomSessionDataProvider;
    private final SessionDataProvider sessionDataProvider;
    private final MovieDataProvider movieDataProvider;
    private final MovieGenreDataProvider movieGenreDataProvider;
    private final ReservationDataProvider reservationDataProvider;
    private final ReservationMovieTicketDataProvider reservationMovieTicketDataProvider;
    private final ReservationRoomSeatDataProvider reservationRoomSeatDataProvider;
    private final MovieTicketDataProvider movieTicketDataProvider;

    public void execute(Long userId, Long movieId) {
        User user = findAdmin(userId);
        checkUserDisabled(user);
        checkUserPermissions(user);

        Movie movie = findMovie(movieId);
        List<Session> findSessions = sessionDataProvider.findByMovieId(movie.getId());

        deleteMovieGenres(movie.getId());
        deleteSessionsReservations(findSessions);
        deleteMovieSessions(movie.getId());
        deleteMovieTickets(movie);
        deleteMovie(movie);
    }

    private User findAdmin(Long userId) {
        return userDataProvider.findByUserId(userId).orElseThrow(UserNotFoundException::new);
    }

    private void checkUserDisabled(User user) {
        if (user.getDisabledAt() != null) {
            throw new UserDisabledException();
        }
    }

    private void checkUserPermissions(User user) {
        List<UserRole> userRoles = userRoleDataProvider.findByUserId(user.getId());

        if (userRoles.isEmpty()) {
            throw new InvalidPermissionsException();
        }

        boolean hasNecessaryRole = userRoles.stream().anyMatch(userRole -> necessaryRoles.contains(userRole.getRole().getName()));

        if (!hasNecessaryRole) {
            throw new InvalidPermissionsException();
        }
    }

    private Movie findMovie(Long movieId) {
        return movieDataProvider.findById(movieId).orElseThrow(MovieNotFoundException::new);
    }

    private List<Session> deleteMovieSessions(Long movieId) {
        return sessionDataProvider.deleteAllByMovie(movieId);
    }

    private Date dateOnEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return calendar.getTime();
    }

    private void deleteSessionsReservations(List<Session> sessions) {
        Date today = dateOnEndOfDay(new Date());

        for (Session session : sessions) {
            deleteRoomSessions(session.getId());
            List<Reservation> deletedReservations = deleteSessionReservations(session.getId());

            for (Reservation reservation : deletedReservations) {
                deleteReservationTickets(reservation.getId(), session.getId());
                deleteReservationSeats(reservation.getId(), session.getId());

                if (dateOnEndOfDay(session.getEnd()).equals(today) || session.getEnd().after(today)) {
                    User userToNotify = reservation.getUser();
                    //todo: enviar e-mail pros usuarios
                }
            }
        }
    }

    private List<Reservation> deleteSessionReservations(Long sessionId) {
        return reservationDataProvider.deleteAllBySessionId(sessionId);
    }

    private void deleteReservationTickets(Long reservationId, Long sessionId) {
        reservationMovieTicketDataProvider.deleteAllByReservationIdAndSessionId(reservationId, sessionId);
    }

    private void deleteReservationSeats(Long reservationId, Long sessionId) {
        reservationRoomSeatDataProvider.deleteAllByReservationIdAndSessionId(reservationId, sessionId);
    }

    private void deleteRoomSessions(Long sessionId) {
        roomSessionDataProvider.deleteAllBySessionId(sessionId);
    }

    private void deleteMovieGenres(Long movieId) {
        movieGenreDataProvider.deleteAllByMovieId(movieId);
    }

    private void deleteMovieTickets(Movie movie) {
        movieTicketDataProvider.deleteByMovie(movie.getId());
    }

    private void deleteMovie(Movie movie) {
        movieDataProvider.delete(movie);
    }
}
