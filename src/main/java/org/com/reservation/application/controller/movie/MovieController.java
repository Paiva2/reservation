package org.com.reservation.application.controller.movie;


import jakarta.validation.Valid;
import org.com.reservation.application.controller.dto.input.user.RegisterMovieInput;
import org.com.reservation.application.controller.dto.output.movie.ListAllMoviesOutput;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequestMapping("/api/v1/movie")
public interface MovieController {
    @PostMapping("/new")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Void> registerMovie(Authentication authentication, @RequestBody @Valid RegisterMovieInput input);

    @DeleteMapping("/{movieId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Void> deleteMovie(Authentication authentication, @PathVariable Long movieId);

    @GetMapping("/list")
    ResponseEntity<ListAllMoviesOutput> listAll(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, @RequestParam(value = "size", required = false, defaultValue = "5") Integer size, @RequestParam(value = "title", required = false) String title, @RequestParam(value = "genre", required = false) String genre, @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date date);
}
