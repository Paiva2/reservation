package org.com.reservation.application.controller.reservationTicket;

import org.com.reservation.application.controller.dto.output.reservationTicket.ValidateReservationTicketOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/reservation-ticket")
public interface ReservationTicketController {
    @GetMapping("/{reservationTicketId}/validate")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<ValidateReservationTicketOutput> validate(Authentication authentication, @PathVariable("reservationTicketId") Long reservationTicketId);
}
