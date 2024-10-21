package org.com.reservation.application.controller.reservation;

import jakarta.validation.Valid;
import org.com.reservation.application.controller.dto.input.reservation.MakeReservationInput;
import org.com.reservation.application.controller.dto.output.reservation.GetAllReservationsFromSessionOutput;
import org.com.reservation.application.controller.dto.output.reservation.ListUserReservationsOutput;
import org.com.reservation.application.controller.dto.output.reservation.MakeReservationOutput;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequestMapping("/api/v1/reservation")
public interface ReservationController {
    @PostMapping("/make/session/{sessionId}/room/{roomId}")
    ResponseEntity<MakeReservationOutput> make(Authentication authentication, @PathVariable("sessionId") Long sessionId, @PathVariable("roomId") Long roomId, @RequestBody @Valid MakeReservationInput input);

    @GetMapping("/list/mine")
    ResponseEntity<ListUserReservationsOutput> listMine(Authentication authentication, @RequestParam(value = "page", required = false, defaultValue = "1") Integer page, @RequestParam(value = "size", required = false, defaultValue = "5") Integer size, @RequestParam(value = "sessionStart", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date sessionStart);

    @DeleteMapping("/{reservationId}/cancel")
    ResponseEntity<Void> cancel(Authentication authentication, @PathVariable("reservationId") Long reservationId);

    @GetMapping("/information/session/{sessionId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<GetAllReservationsFromSessionOutput> getInformation(Authentication authentication, @PathVariable("sessionId") Long sessionId, @RequestParam(value = "page", required = false, defaultValue = "1") Integer page, @RequestParam(value = "size", required = false, defaultValue = "5") Integer size);

    @DeleteMapping("{reservationId}/user/{userId}/cancel")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Void> cancelReservationAdmin(Authentication authentication, @PathVariable("reservationId") Long reservationId, @PathVariable("userId") Long userId);
}
