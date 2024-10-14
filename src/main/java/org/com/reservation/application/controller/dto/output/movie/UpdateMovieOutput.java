package org.com.reservation.application.controller.dto.output.movie;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.com.reservation.domain.entity.Genre;
import org.com.reservation.domain.entity.Movie;
import org.com.reservation.domain.entity.MovieGenre;
import org.com.reservation.domain.interfaces.utils.DateUtils;
import org.com.reservation.infra.utils.DateUtilsImpl;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UpdateMovieOutput {
    private Long id;
    private String title;
    private String description;
    private String posterImage;
    private String trailerVideoUrl;
    private Long durationSeconds;
    private String releaseDate;
    private String studioName;
    private String cast;
    private MovieTicketOutput ticket;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<GenreOutput> genres;

    private final static DateUtils dateUtils = new DateUtilsImpl();

    public static UpdateMovieOutput toOutput(Movie movie) {
        return UpdateMovieOutput.builder()
            .id(movie.getId())
            .title(movie.getTitle())
            .description(movie.getDescription())
            .posterImage(movie.getPosterImage())
            .trailerVideoUrl(movie.getTrailerVideoUrl())
            .durationSeconds(movie.getDurationSeconds())
            .releaseDate(dateUtils.formatDate(movie.getReleaseDate()))
            .studioName(movie.getStudioName())
            .cast(movie.getCast())
            .genres(movie.getMovieGenres() != null ? movie.getMovieGenres().stream().map(MovieGenre::getGenre).map(UpdateMovieOutput::toGenreOutput).toList() : null)
            .ticket(MovieTicketOutput.builder()
                .id(movie.getMovieTicket().getId())
                .normalPrice(movie.getMovieTicket().getNormalPrice().toString())
                .studentPrice(movie.getMovieTicket().getStudentPrice().toString())
                .specialPrice(movie.getMovieTicket().getSpecialPrice().toString())
                .isFree(movie.getMovieTicket().getIsFree())
                .build()
            ).build();
    }

    private static GenreOutput toGenreOutput(Genre genre) {
        return GenreOutput.builder()
            .id(genre.getId())
            .name(genre.getName())
            .build();
    }

    @AllArgsConstructor
    @Builder
    @Data
    private static class MovieTicketOutput {
        private Long id;
        private String normalPrice;
        private String studentPrice;
        private String specialPrice;
        private Boolean isFree;
    }

    @AllArgsConstructor
    @Builder
    @Data
    private static class GenreOutput {
        private Long id;
        private String name;
    }
}
