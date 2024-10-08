package org.com.reservation.application.controller.movie;

import lombok.AllArgsConstructor;
import org.com.reservation.application.controller.dto.input.user.RegisterMovieInput;
import org.com.reservation.domain.usecase.movie.registerMovie.RegisterMovieUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class MovieControllerImpl implements MovieController {
    private final RegisterMovieUsecase registerMovieUsecase;

    @Override
    @Transactional
    public ResponseEntity<Void> registerMovie(Authentication authentication, RegisterMovieInput input) {
        Long subject = Long.valueOf(authentication.getName());
        registerMovieUsecase.execute(subject, input);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
