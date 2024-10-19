package org.com.reservation.domain.usecase.reservation.getAllReservationsFromSession;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.com.reservation.application.controller.dto.output.reservation.GetAllReservationsFromSessionOutput;
import org.com.reservation.domain.entity.*;
import org.com.reservation.domain.enumeration.EnumRole;
import org.com.reservation.domain.interfaces.dataprovider.*;
import org.com.reservation.domain.usecase.common.exception.InvalidPermissionsException;
import org.com.reservation.domain.usecase.common.exception.SessionNotFoundException;
import org.com.reservation.domain.usecase.common.exception.UserDisabledException;
import org.com.reservation.domain.usecase.common.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Builder
public class GetAllReservationsFromSessionUsecase {
    private final static List<EnumRole> necessaryRoles = List.of(EnumRole.ADMIN);

    private final UserDataProvider userDataProvider;
    private final UserRoleDataProvider userRoleDataProvider;
    private final SessionDataProvider sessionDataProvider;
    private final ReservationDataProvider reservationDataProvider;

    public GetAllReservationsFromSessionOutput execute(Long userId, Long sessionId, int page, int size) {
        User user = findUser(userId);
        checkUserDisabled(user);

        List<UserRole> userRoles = findUserRoles(user);
        checkUserPermissions(userRoles);

        if (page < 1) {
            page = 1;
        }

        if (size < 1) {
            size = 5;
        } else if (size > 50) {
            size = 50;
        }

        Session session = findSession(sessionId);
        Page<Reservation> sessionReservations = findReservations(session.getId(), page, size);

        List<BigDecimal> allReservationsTicketsPaidGrouped = getSessionReservationsTicketsPaidGrouped(sessionReservations.getContent());
        BigDecimal sessionTotalRevenue = calculateTotalRevenueFromSession(allReservationsTicketsPaidGrouped);

        return mountOutput(sessionReservations, session, sessionTotalRevenue);
    }

    private User findUser(Long userId) {
        return userDataProvider.findByUserId(userId).orElseThrow(UserNotFoundException::new);
    }

    private void checkUserDisabled(User user) {
        if (user.getDisabledAt() != null) {
            throw new UserDisabledException();
        }
    }

    private List<UserRole> findUserRoles(User user) {
        return userRoleDataProvider.findByUserId(user.getId());
    }

    private void checkUserPermissions(List<UserRole> userRoles) {
        if (userRoles == null || userRoles.isEmpty()) {
            throw new InvalidPermissionsException();
        }

        boolean hasPermission = userRoles.stream().anyMatch(userRole -> necessaryRoles.contains(userRole.getRole().getName()));

        if (!hasPermission) {
            throw new InvalidPermissionsException();
        }
    }

    private Session findSession(Long sessionId) {
        return sessionDataProvider.findById(sessionId).orElseThrow(SessionNotFoundException::new);
    }

    private Page<Reservation> findReservations(Long sessionId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "createdAt");
        return reservationDataProvider.findAllBySessionPageable(sessionId, pageable);
    }

    private List<BigDecimal> getSessionReservationsTicketsPaidGrouped(List<Reservation> sessionReservations) {
        return sessionReservations.stream()
            .flatMap(reservation -> reservation.getReservationTickets().stream())
            .map(ReservationTicket::getPricePaid)
            .toList();
    }

    private BigDecimal calculateTotalRevenueFromSession(List<BigDecimal> sessionReservationsTicketsPaidValues) {
        return sessionReservationsTicketsPaidValues.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private GetAllReservationsFromSessionOutput mountOutput(Page<Reservation> sessionReservations, Session session, BigDecimal sessionRevenue) {
        return GetAllReservationsFromSessionOutput.toOutput(sessionReservations, session, sessionRevenue);
    }
}
