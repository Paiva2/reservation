package org.com.reservation.domain.usecase.session.listAllSessions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.com.reservation.application.controller.dto.output.session.ListAllSessionsOutput;
import org.com.reservation.domain.entity.Session;
import org.com.reservation.domain.entity.User;
import org.com.reservation.domain.entity.UserRole;
import org.com.reservation.domain.enumeration.EnumRole;
import org.com.reservation.domain.interfaces.dataprovider.SessionDataProvider;
import org.com.reservation.domain.interfaces.dataprovider.UserDataProvider;
import org.com.reservation.domain.interfaces.dataprovider.UserRoleDataProvider;
import org.com.reservation.domain.usecase.common.exception.InvalidPermissionsException;
import org.com.reservation.domain.usecase.common.exception.UserDisabledException;
import org.com.reservation.domain.usecase.common.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Builder
public class ListAllSessionsUsecase {
    private final static List<EnumRole> necessaryRoles = List.of(EnumRole.ADMIN);

    private final UserDataProvider userDataProvider;
    private final UserRoleDataProvider userRoleDataProvider;
    private final SessionDataProvider sessionDataProvider;

    public ListAllSessionsOutput execute(Long userId, int page, int size, Date date, Boolean active) {
        User user = findUser(userId);
        checkUserDisabled(user);

        List<UserRole> userRoles = findUserRoles(user);
        checkUserPermissions(userRoles);

        if (page < 1) {
            page = 1;
        }

        if (size < 1) {
            size = 5;
        }

        if (size > 50) {
            size = 50;
        }

        Page<Session> sessionsPage = findAllSessions(page, size, date, active);

        return mountOutput(sessionsPage);
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

        boolean userHasNecessaryRoles = userRoles.stream().anyMatch(userRole -> necessaryRoles.contains(userRole.getRole().getName()));

        if (!userHasNecessaryRoles) {
            throw new InvalidPermissionsException();
        }
    }

    private Page<Session> findAllSessions(int page, int size, Date date, Boolean active) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "SS_CREATED_AT");
        return sessionDataProvider.findAllSessions(pageable, date, active);
    }

    private ListAllSessionsOutput mountOutput(Page<Session> sessions) {
        return ListAllSessionsOutput.builder()
            .page(sessions.getNumber() + 1)
            .size(sessions.getSize())
            .totalItems(sessions.getTotalElements())
            .totalPages(sessions.getTotalPages())
            .isLastPage(sessions.isLast())
            .items(sessions.getContent().stream().map(ListAllSessionsOutput.SessionOutput::new).toList())
            .build();
    }
}
