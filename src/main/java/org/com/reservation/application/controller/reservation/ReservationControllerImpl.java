package org.com.reservation.application.controller.reservation;

import lombok.AllArgsConstructor;
import org.com.reservation.application.controller.dto.input.reservation.MakeReservationInput;
import org.com.reservation.application.controller.dto.output.reservation.ListUserReservationsOutput;
import org.com.reservation.application.controller.dto.output.reservation.MakeReservationOutput;
import org.com.reservation.domain.usecase.reservation.listUserReservations.ListUserReservationsUsecase;
import org.com.reservation.domain.usecase.reservation.makeReservation.MakeReservationUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@AllArgsConstructor
@RestController
public class ReservationControllerImpl implements ReservationController {
    private final MakeReservationUsecase makeReservationUsecase;
    private final ListUserReservationsUsecase listUserReservationsUsecase;

    @Override
    @Transactional
    public ResponseEntity<MakeReservationOutput> make(Authentication authentication, Long sessionId, Long roomId, MakeReservationInput input) {
        Long subject = Long.parseLong(authentication.getName());
        MakeReservationOutput output = makeReservationUsecase.execute(subject, sessionId, roomId, input);
        return new ResponseEntity<>(output, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ListUserReservationsOutput> listMine(Authentication authentication, Integer page, Integer size, Date sessionStart) {
        Long subject = Long.parseLong(authentication.getName());
        ListUserReservationsOutput output = listUserReservationsUsecase.execute(subject, page, size, sessionStart);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
