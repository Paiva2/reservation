package org.com.reservation.application.controller.reservation;

import lombok.AllArgsConstructor;
import org.com.reservation.application.controller.dto.input.reservation.MakeReservationInput;
import org.com.reservation.application.controller.dto.output.reservation.MakeReservationOutput;
import org.com.reservation.domain.usecase.reservation.makeReservation.MakeReservationUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class ReservationControllerImpl implements ReservationController {
    private final MakeReservationUsecase makeReservationUsecase;

    @Override
    @Transactional
    public ResponseEntity<MakeReservationOutput> make(Authentication authentication, Long sessionId, Long roomId, MakeReservationInput input) {
        Long subject = Long.parseLong(authentication.getName());
        MakeReservationOutput output = makeReservationUsecase.execute(subject, sessionId, roomId, input);
        return new ResponseEntity<>(output, HttpStatus.CREATED);
    }
}
