package org.com.reservation.application.controller.movie;

import lombok.AllArgsConstructor;
import org.com.reservation.application.controller.dto.input.movie.UpdateMovieInput;
import org.com.reservation.application.controller.dto.input.user.RegisterMovieInput;
import org.com.reservation.application.controller.dto.output.movie.ListAllMoviesOutput;
import org.com.reservation.application.controller.dto.output.movie.UpdateMovieOutput;
import org.com.reservation.domain.usecase.movie.deleteMovie.DeleteMovieUsecase;
import org.com.reservation.domain.usecase.movie.listAllMovies.ListAllMoviesUsecase;
import org.com.reservation.domain.usecase.movie.registerMovie.RegisterMovieUsecase;
import org.com.reservation.domain.usecase.movie.updateMovie.UpdateMovieUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@AllArgsConstructor
public class MovieControllerImpl implements MovieController {
    private final RegisterMovieUsecase registerMovieUsecase;
    private final DeleteMovieUsecase deleteMovieUsecase;
    private final ListAllMoviesUsecase listAllMoviesUsecase;
    private final UpdateMovieUsecase updateMovieUsecase;

    @Override
    @Transactional
    public ResponseEntity<Void> registerMovie(Authentication authentication, RegisterMovieInput input) {
        Long subject = Long.valueOf(authentication.getName());
        registerMovieUsecase.execute(subject, input);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteMovie(Authentication authentication, Long movieId) {
        Long subject = Long.valueOf(authentication.getName());
        deleteMovieUsecase.execute(subject, movieId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ListAllMoviesOutput> listAll(Integer page, Integer size, String title, String genre, Date date) {
        ListAllMoviesOutput output = listAllMoviesUsecase.execute(page, size, title, genre, date);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<UpdateMovieOutput> update(Authentication authentication, Long movieId, UpdateMovieInput input) {
        Long subject = Long.valueOf(authentication.getName());
        UpdateMovieOutput output = updateMovieUsecase.execute(subject, movieId, input);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
