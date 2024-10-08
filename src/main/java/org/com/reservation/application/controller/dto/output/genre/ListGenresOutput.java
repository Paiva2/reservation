package org.com.reservation.application.controller.dto.output.genre;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.com.reservation.domain.entity.Genre;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ListGenresOutput {
    private int page;
    private int size;
    private boolean isLast;
    private Long totalItems;
    private int totalPages;
    private List<GenreOutput> items;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class GenreOutput {
        private Long id;
        private String name;
    }

    public static GenreOutput convertGenre(Genre genre) {
        return GenreOutput.builder()
            .id(genre.getId())
            .name(genre.getName())
            .build();
    }
}
