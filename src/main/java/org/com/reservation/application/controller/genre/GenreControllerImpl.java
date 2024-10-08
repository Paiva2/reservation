package org.com.reservation.application.controller.genre;

import lombok.AllArgsConstructor;
import org.com.reservation.application.controller.dto.output.genre.ListGenresOutput;
import org.com.reservation.domain.usecase.genre.listGenresUsecase.ListGenresUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class GenreControllerImpl implements GenreController {
    private final ListGenresUsecase listGenresUsecase;

    @Override
    public ResponseEntity<ListGenresOutput> list(Integer page, Integer size) {
        ListGenresOutput output = listGenresUsecase.execute(page, size);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
