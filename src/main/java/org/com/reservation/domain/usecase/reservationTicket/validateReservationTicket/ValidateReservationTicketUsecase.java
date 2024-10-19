package org.com.reservation.domain.usecase.reservationTicket.validateReservationTicket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.com.reservation.application.controller.dto.output.reservationTicket.ValidateReservationTicketOutput;
import org.com.reservation.domain.entity.*;
import org.com.reservation.domain.enumeration.EnumRole;
import org.com.reservation.domain.interfaces.dataprovider.ReservationTicketDataProvider;
import org.com.reservation.domain.interfaces.dataprovider.SessionDataProvider;
import org.com.reservation.domain.interfaces.dataprovider.UserDataProvider;
import org.com.reservation.domain.interfaces.dataprovider.UserRoleDataProvider;
import org.com.reservation.domain.usecase.common.exception.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Builder
public class ValidateReservationTicketUsecase {
    private final static List<EnumRole> necessaryRoles = List.of(EnumRole.ADMIN);

    private final UserDataProvider userDataProvider;
    private final UserRoleDataProvider userRoleDataProvider;
    private final ReservationTicketDataProvider reservationTicketDataProvider;
    private final SessionDataProvider sessionDataProvider;

    public ValidateReservationTicketOutput execute(Long userId, Long reservationTicketId) {
        User user = findUser(userId);
        checkUserDisabled(user);

        List<UserRole> userRoles = findUserRoles(user.getId());
        checkUserPermissions(userRoles);

        ReservationTicket reservationTicket = findReservationTicket(reservationTicketId);
        Reservation reservation = reservationTicket.getReservation();
        Session session = findSessionByReservationId(reservation.getId());

        return mountOutput(reservationTicket, reservation, session);
    }

    private User findUser(Long userId) {
        return userDataProvider.findByUserId(userId).orElseThrow(UserNotFoundException::new);
    }

    private void checkUserDisabled(User user) {
        if (user.getDisabledAt() != null) {
            throw new UserDisabledException();
        }
    }

    private List<UserRole> findUserRoles(Long userId) {
        return userRoleDataProvider.findByUserId(userId);
    }

    private void checkUserPermissions(List<UserRole> userRoles) {
        if (userRoles == null || userRoles.isEmpty()) {
            throw new InvalidPermissionsException();
        }

        boolean hasPermission = userRoles.stream().map(UserRole::getRole).anyMatch(role -> necessaryRoles.contains(role.getName()));

        if (!hasPermission) {
            throw new InvalidPermissionsException();
        }
    }

    private ReservationTicket findReservationTicket(Long reservationTicketId) {
        return reservationTicketDataProvider.findById(reservationTicketId).orElseThrow(ReservationTicketNotFoundException::new);
    }

    private Session findSessionByReservationId(Long reservationId) {
        return sessionDataProvider.findByReservationId(reservationId).orElseThrow(SessionNotFoundException::new);
    }

    private Boolean sessionNotStarted(Session session) {
        return session.getStart().after(new Date());
    }

    private ValidateReservationTicketOutput mountOutput(ReservationTicket reservationTicket, Reservation reservation, Session session) {
        return ValidateReservationTicketOutput.builder()
            .reservationTicketId(reservationTicket.getId())
            .reservationId(reservation.getId())
            .exists(true)
            .validForUse(sessionNotStarted(session))
            .build();
    }
}
