package org.com.reservation.application.controller.reservation;

import jakarta.validation.Valid;
import org.com.reservation.application.controller.dto.input.reservation.MakeReservationInput;
import org.com.reservation.application.controller.dto.output.reservation.MakeReservationOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/reservation")
public interface ReservationController {
    @PostMapping("/make/session/{sessionId}/room/{roomId}")
    ResponseEntity<MakeReservationOutput> make(Authentication authentication, @PathVariable("sessionId") Long sessionId, @PathVariable("roomId") Long roomId, @RequestBody @Valid MakeReservationInput input);
}
