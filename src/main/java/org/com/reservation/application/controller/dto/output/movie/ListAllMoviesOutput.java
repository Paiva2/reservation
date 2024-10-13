package org.com.reservation.application.controller.dto.output.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.com.reservation.domain.entity.Genre;
import org.com.reservation.domain.entity.Movie;
import org.com.reservation.domain.entity.MovieGenre;
import org.com.reservation.domain.entity.Session;
import org.com.reservation.domain.interfaces.utils.DateUtils;
import org.com.reservation.infra.utils.DateUtilsImpl;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class ListAllMoviesOutput {
    private Integer page;
    private Integer size;
    private Long totalElements;
    private Integer totalPages;
    private Boolean isLastPage;
    private List<MovieOutput> movies;

    private static final DateUtils dateUtils = new DateUtilsImpl();

    public static MovieOutput toMovieOutput(Movie movie) {
        return MovieOutput.builder()
            .id(movie.getId())
            .title(movie.getTitle())
            .description(movie.getDescription())
            .posterImage(movie.getPosterImage())
            .trailerVideoUrl(movie.getTrailerVideoUrl())
            .durationSeconds(movie.getDurationSeconds())
            .releaseDate(dateUtils.formatDate(movie.getReleaseDate()))
            .studioName(movie.getStudioName())
            .cast(movie.getCast())
            .ticket(MovieTicketOutput.builder()
                .id(movie.getMovieTicket().getId())
                .normalPrice(movie.getMovieTicket().getNormalPrice().toString())
                .studentPrice(movie.getMovieTicket().getStudentPrice().toString())
                .specialPrice(movie.getMovieTicket().getSpecialPrice().toString())
                .isFree(movie.getMovieTicket().getIsFree())
                .build())
            .genres(movie.getMovieGenres().stream().map(MovieGenre::getGenre).map(ListAllMoviesOutput::toGenreOutput).toList())
            .sessions(movie.getSessions().stream().map(ListAllMoviesOutput::toSessionOutput).toList())
            .build();
    }

    private static GenreOutput toGenreOutput(Genre genre) {
        return GenreOutput.builder()
            .id(genre.getId())
            .name(genre.getName())
            .build();
    }

    private static SessionOutput toSessionOutput(Session session) {
        return SessionOutput.builder()
            .id(session.getId())
            .start(dateUtils.formatDateTime(session.getStart()))
            .end(dateUtils.formatDateTime(session.getEnd()))
            .active(session.isActive())
            .build();
    }

    @AllArgsConstructor
    @Builder
    @Data
    public static class MovieOutput {
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
        private List<GenreOutput> genres;
        private List<SessionOutput> sessions;
    }

    @AllArgsConstructor
    @Builder
    @Data
    public static class GenreOutput {
        private Long id;
        private String name;
    }

    @AllArgsConstructor
    @Builder
    @Data
    public static class MovieTicketOutput {
        private Long id;
        private String normalPrice;
        private String studentPrice;
        private String specialPrice;
        private Boolean isFree;
    }

    @AllArgsConstructor
    @Builder
    @Data
    public static class SessionOutput {
        private Long id;
        private String start;
        private String end;
        private boolean active;
    }
}
