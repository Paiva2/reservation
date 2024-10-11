package org.com.reservation.application.controller.session;

import jakarta.validation.Valid;
import org.com.reservation.application.controller.dto.input.session.CreateSessionInput;
import org.com.reservation.application.controller.dto.output.session.CreateSessionOutput;
import org.com.reservation.application.controller.dto.output.session.ListUpcomingSessionsOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/session")
public interface SessionController {
    @PostMapping("/new/movie/{movieId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<CreateSessionOutput> newSession(Authentication authentication, @PathVariable("movieId") Long movieId, @RequestBody @Valid CreateSessionInput input);

    @GetMapping("/list/upcoming")
    ResponseEntity<ListUpcomingSessionsOutput> listUpcoming(@RequestParam(value = "movieId", required = false) Long movieId, @RequestParam(value = "page", required = false, defaultValue = "1") Integer page, @RequestParam(value = "size", required = false, defaultValue = "5") Integer size);
}
