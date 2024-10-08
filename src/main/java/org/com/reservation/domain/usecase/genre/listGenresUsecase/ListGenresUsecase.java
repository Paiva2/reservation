package org.com.reservation.domain.usecase.genre.listGenresUsecase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.com.reservation.application.controller.dto.output.genre.ListGenresOutput;
import org.com.reservation.domain.entity.Genre;
import org.com.reservation.domain.interfaces.dataprovider.GenreDataProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@AllArgsConstructor
@Builder
public class ListGenresUsecase {
    private final GenreDataProvider genreDataProvider;

    public ListGenresOutput execute(int page, int size) {
        if (page < 1) {
            page = 1;
        }

        if (size < 5) {
            size = 5;
        }

        PageRequest pageable = PageRequest.of(page - 1, size, Sort.Direction.ASC, "name");

        Page<Genre> genres = getAllGenres(pageable);
        List<ListGenresOutput.GenreOutput> outputList = genres.stream().map(ListGenresOutput::convertGenre).toList();

        return mountOutput(genres.getNumber(), genres.getSize(), genres.getTotalPages(), genres.isLast(), genres.getTotalElements(), outputList);
    }

    private Page<Genre> getAllGenres(PageRequest pageable) {
        return genreDataProvider.listAll(pageable);
    }

    private ListGenresOutput mountOutput(int page, int size, int totalPages, boolean isLast, long totalItems, List<ListGenresOutput.GenreOutput> list) {
        return ListGenresOutput.builder()
            .page(page + 1)
            .size(size)
            .isLast(isLast)
            .totalItems(totalItems)
            .totalPages(totalPages)
            .items(list)
            .build();
    }
}
