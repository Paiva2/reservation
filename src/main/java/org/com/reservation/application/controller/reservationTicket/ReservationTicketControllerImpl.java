package org.com.reservation.application.controller.reservationTicket;

import lombok.AllArgsConstructor;
import org.com.reservation.application.controller.dto.output.reservationTicket.ValidateReservationTicketOutput;
import org.com.reservation.domain.usecase.reservationTicket.validateReservationTicket.ValidateReservationTicketUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ReservationTicketControllerImpl implements ReservationTicketController {
    private final ValidateReservationTicketUsecase validateReservationTicketUsecase;

    @Override
    public ResponseEntity<ValidateReservationTicketOutput> validate(Authentication authentication, Long reservationTicketId) {
        Long subject = Long.parseLong(authentication.getName());
        ValidateReservationTicketOutput output = validateReservationTicketUsecase.execute(subject, reservationTicketId);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
