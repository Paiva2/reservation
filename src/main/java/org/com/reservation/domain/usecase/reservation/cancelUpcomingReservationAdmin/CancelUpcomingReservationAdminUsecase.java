package org.com.reservation.domain.usecase.reservation.cancelUpcomingReservationAdmin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.com.reservation.domain.entity.User;
import org.com.reservation.domain.entity.UserRole;
import org.com.reservation.domain.enumeration.EnumRole;
import org.com.reservation.domain.interfaces.dataprovider.UserDataProvider;
import org.com.reservation.domain.interfaces.dataprovider.UserRoleDataProvider;
import org.com.reservation.domain.usecase.common.exception.InvalidPermissionsException;
import org.com.reservation.domain.usecase.common.exception.UserDisabledException;
import org.com.reservation.domain.usecase.common.exception.UserNotFoundException;
import org.com.reservation.domain.usecase.reservation.cancelUpcomingReservation.CancelUpcomingReservationUsecase;

import java.util.List;

@AllArgsConstructor
@Builder
public class CancelUpcomingReservationAdminUsecase {
    private final static List<EnumRole> necessaryRoles = List.of(EnumRole.ADMIN);

    private final UserDataProvider userDataProvider;
    private final UserRoleDataProvider userRoleDataProvider;
    private final CancelUpcomingReservationUsecase cancelUpcomingReservationUsecase;

    public void execute(Long adminId, Long userIdToCancelReservation, Long reservationId) {
        User admin = findAdmin(adminId);
        checkAdminDisabled(admin);

        List<UserRole> adminRoles = findUserRoles(admin.getId());
        checkNecessaryAdminRoles(adminRoles);

        cancelUpcomingReservationUsecase.execute(userIdToCancelReservation, reservationId);
    }

    private User findAdmin(Long adminId) {
        return userDataProvider.findByUserId(adminId).orElseThrow(UserNotFoundException::new);
    }

    private void checkAdminDisabled(User admin) {
        if (admin.getDisabledAt() != null) {
            throw new UserDisabledException();
        }
    }

    private List<UserRole> findUserRoles(Long adminId) {
        return userRoleDataProvider.findByUserId(adminId);
    }

    private void checkNecessaryAdminRoles(List<UserRole> adminRoles) {
        if (adminRoles == null || adminRoles.isEmpty()) {
            throw new InvalidPermissionsException();
        }

        boolean hasNecessaryRole = adminRoles.stream().anyMatch(adminRole -> necessaryRoles.contains(adminRole.getRole().getName()));

        if (!hasNecessaryRole) {
            throw new InvalidPermissionsException();
        }
    }
}
