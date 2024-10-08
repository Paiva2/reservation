package org.com.reservation.application.controller.movie;


import jakarta.validation.Valid;
import org.com.reservation.application.controller.dto.input.user.RegisterMovieInput;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/movie")
public interface MovieController {
    @PostMapping("/new")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Void> registerMovie(Authentication authentication, @RequestBody @Valid RegisterMovieInput input);
}
