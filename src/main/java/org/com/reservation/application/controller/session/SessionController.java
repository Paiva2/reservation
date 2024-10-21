package org.com.reservation.application.controller.session;

import jakarta.validation.Valid;
import org.com.reservation.application.controller.dto.input.session.CreateSessionInput;
import org.com.reservation.application.controller.dto.output.roomSeat.GetAvailableRoomSeatsOnSessionOutput;
import org.com.reservation.application.controller.dto.output.session.CreateSessionOutput;
import org.com.reservation.application.controller.dto.output.session.ListAllSessionsOutput;
import org.com.reservation.application.controller.dto.output.session.ListUpcomingSessionsOutput;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequestMapping("/api/v1/session")
public interface SessionController {
    @PostMapping("/new/movie/{movieId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<CreateSessionOutput> newSession(Authentication authentication, @PathVariable("movieId") Long movieId, @RequestBody @Valid CreateSessionInput input);

    @GetMapping("/list/upcoming")
    ResponseEntity<ListUpcomingSessionsOutput> listUpcoming(@RequestParam(value = "movieId", required = false) Long movieId, @RequestParam(value = "page", required = false, defaultValue = "1") Integer page, @RequestParam(value = "size", required = false, defaultValue = "5") Integer size);

    @GetMapping("{sessionId}/room/{roomId}/seats/available")
    ResponseEntity<GetAvailableRoomSeatsOnSessionOutput> getAvailableSeatsOnSession(@PathVariable Long sessionId, @PathVariable Long roomId);

    @GetMapping("/list-all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<ListAllSessionsOutput> listAll(Authentication authentication, @RequestParam(value = "page", required = false, defaultValue = "1") Integer page, @RequestParam(value = "size", required = false, defaultValue = "5") Integer size, @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date date, @RequestParam(value = "active", required = false) Boolean active);
}
