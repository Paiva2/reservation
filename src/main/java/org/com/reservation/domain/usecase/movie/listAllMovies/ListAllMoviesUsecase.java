package org.com.reservation.domain.usecase.movie.listAllMovies;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.com.reservation.application.controller.dto.output.movie.ListAllMoviesOutput;
import org.com.reservation.domain.entity.Movie;
import org.com.reservation.domain.interfaces.dataprovider.MovieDataProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;

@AllArgsConstructor
@Builder
public class ListAllMoviesUsecase {
    private final MovieDataProvider movieDataProvider;

    public ListAllMoviesOutput execute(int page, int size, String title, String genre, Date date) {
        if (page < 1) {
            page = 1;
        }

        if (size < 5) {
            size = 5;
        } else if (size > 50) {
            size = 50;
        }

        Page<Movie> moviesPage = findMovies(page, size, title, genre, date);

        return mountOutput(moviesPage);
    }

    private Page<Movie> findMovies(int page, int size, String title, String genre, Date date) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.ASC, "MO_TITLE");
        return movieDataProvider.findAllPageable(pageable, title, genre, date);
    }

    private ListAllMoviesOutput mountOutput(Page<Movie> moviesPage) {
        return ListAllMoviesOutput.builder()
            .page(moviesPage.getNumber() + 1)
            .size(moviesPage.getSize())
            .totalElements(moviesPage.getTotalElements())
            .totalPages(moviesPage.getTotalPages())
            .isLastPage(moviesPage.isLast())
            .movies(moviesPage.stream().map(ListAllMoviesOutput::toMovieOutput).toList())
            .build();
    }
}
