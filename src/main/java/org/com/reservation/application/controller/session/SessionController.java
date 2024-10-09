package org.com.reservation.application.controller.session;

import jakarta.validation.Valid;
import org.com.reservation.application.controller.dto.input.session.CreateSessionInput;
import org.com.reservation.application.controller.dto.output.session.CreateSessionOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/session")
public interface SessionController {
    @PostMapping("/new/movie/{movieId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<CreateSessionOutput> newSession(Authentication authentication, @PathVariable("movieId") Long movieId, @RequestBody @Valid CreateSessionInput input);
}
