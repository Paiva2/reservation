package org.com.reservation.domain.usecase.room.listAvailableRooms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.com.reservation.application.controller.dto.output.room.ListAvailableRoomsOutput;
import org.com.reservation.domain.entity.Room;
import org.com.reservation.domain.entity.User;
import org.com.reservation.domain.entity.UserRole;
import org.com.reservation.domain.enumeration.EnumRole;
import org.com.reservation.domain.interfaces.dataprovider.RoomDataProvider;
import org.com.reservation.domain.interfaces.dataprovider.UserDataProvider;
import org.com.reservation.domain.interfaces.dataprovider.UserRoleDataProvider;
import org.com.reservation.domain.usecase.common.exception.InvalidPermissionsException;
import org.com.reservation.domain.usecase.common.exception.UserNotFoundException;
import org.com.reservation.domain.usecase.common.exception.UserDisabledException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@AllArgsConstructor
@Builder
public class ListAvailableRoomsUsecase {
    private final List<EnumRole> necessaryRoles = List.of(EnumRole.ADMIN);

    private final UserDataProvider userDataProvider;
    private final RoomDataProvider roomDataProvider;
    private final UserRoleDataProvider userRoleDataProvider;

    public ListAvailableRoomsOutput execute(Long userId, int page, int size) {
        User admin = findAdmin(userId);
        checkUserEnabled(admin);
        checkUserPermissions(admin);

        if (page < 1) {
            page = 1;
        }

        if (size < 5) {
            size = 5;
        } else if (size > 50) {
            size = 50;
        }

        Page<Room> roomsPage = findAvailableRooms(page, size);

        return mountOutput(roomsPage);
    }

    private User findAdmin(Long userId) {
        return userDataProvider.findByUserId(userId).orElseThrow(UserNotFoundException::new);
    }

    private void checkUserEnabled(User admin) {
        if (admin.getDisabledAt() != null) {
            throw new UserDisabledException();
        }
    }

    private void checkUserPermissions(User admin) {
        List<UserRole> userRoles = userRoleDataProvider.findByUserId(admin.getId());

        if (userRoles.isEmpty()) {
            throw new InvalidPermissionsException();
        }

        boolean userHasNecessaryRole = userRoles.stream().anyMatch(userRole -> necessaryRoles.contains(userRole.getRole().getName()));

        if (!userHasNecessaryRole) {
            throw new InvalidPermissionsException();
        }
    }

    private Page<Room> findAvailableRooms(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.ASC, "number");
        return roomDataProvider.findAvailables(pageable);
    }

    private ListAvailableRoomsOutput mountOutput(Page<Room> roomsPage) {
        return ListAvailableRoomsOutput.builder()
            .page(roomsPage.getNumber() + 1)
            .size(roomsPage.getSize())
            .totalPages(roomsPage.getTotalPages())
            .totalItems(roomsPage.getTotalElements())
            .isLast(roomsPage.isLast())
            .rooms(roomsPage.map(ListAvailableRoomsOutput::convertRoom).stream().toList())
            .build();
    }
}
