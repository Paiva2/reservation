package org.com.reservation.domain.usecase.reservation.listUserReservations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.com.reservation.application.controller.dto.output.reservation.ListUserReservationsOutput;
import org.com.reservation.domain.entity.Reservation;
import org.com.reservation.domain.entity.User;
import org.com.reservation.domain.interfaces.dataprovider.ReservationDataProvider;
import org.com.reservation.domain.interfaces.dataprovider.UserDataProvider;
import org.com.reservation.domain.usecase.common.exception.UserDisabledException;
import org.com.reservation.domain.usecase.common.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;

@AllArgsConstructor
@Builder
public class ListUserReservationsUsecase {
    private final UserDataProvider userDataProvider;
    private final ReservationDataProvider reservationDataProvider;

    public ListUserReservationsOutput execute(Long userId, int page, int size, Date sessionStart) {
        User user = findUser(userId);
        checkUserActive(user);

        if (page < 1) {
            page = 1;
        }

        if (size < 1) {
            size = 5;
        } else if (size > 50) {
            size = 50;
        }

        Page<Reservation> reservationsPage = findReservationsPage(userId, page, size, sessionStart);

        return mountOutput(reservationsPage);
    }

    private User findUser(Long userId) {
        return userDataProvider.findByUserId(userId).orElseThrow(UserNotFoundException::new);
    }

    private void checkUserActive(User user) {
        if (user.getDisabledAt() != null) {
            throw new UserDisabledException();
        }
    }

    private Page<Reservation> findReservationsPage(Long userId, int page, int size, Date sessionStart) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "RES_CREATED_AT");
        return reservationDataProvider.findAllByUserFetchingAdditionals(pageable, userId, sessionStart);
    }

    private ListUserReservationsOutput mountOutput(Page<Reservation> reservationsPage) {
        return ListUserReservationsOutput.builder()
            .page(reservationsPage.getNumber() + 1)
            .size(reservationsPage.getSize())
            .totalPages(reservationsPage.getTotalPages())
            .isLast(reservationsPage.isLast())
            .totalElements(reservationsPage.getTotalElements())
            .items(reservationsPage.getContent().stream().map(ListUserReservationsOutput::toReservationOutput).toList())
            .build();
    }
}
