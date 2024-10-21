package org.com.reservation.application.controller.dto.output.session;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.com.reservation.domain.entity.Movie;
import org.com.reservation.domain.entity.RoomSession;
import org.com.reservation.domain.entity.Session;
import org.com.reservation.domain.interfaces.utils.DateUtils;
import org.com.reservation.infra.utils.DateUtilsImpl;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ListAllSessionsOutput {
    private Integer page;
    private Integer size;
    private Long totalItems;
    private Integer totalPages;
    private Boolean isLastPage;
    private List<SessionOutput> items;

    private static final DateUtils dateUtils = new DateUtilsImpl();

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class SessionOutput {
        private Long id;
        private String start;
        private String end;
        private Boolean active;

        private MovieOutput movie;
        private List<RoomOutput> rooms;

        public SessionOutput(Session session) {
            this.id = session.getId();
            this.start = dateUtils.formatDateTime(session.getStart());
            this.end = dateUtils.formatDateTime(session.getEnd());
            this.active = session.isActive();
            this.movie = new MovieOutput(session.getMovie());
            this.rooms = session.getRoomSessions().stream().map(ListAllSessionsOutput::toRoomOutput).toList();
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
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
        private TicketOutput ticket;

        public MovieOutput(Movie movie) {
            this.id = movie.getId();
            this.title = movie.getTitle();
            this.description = movie.getDescription();
            this.posterImage = movie.getPosterImage();
            this.trailerVideoUrl = movie.getTrailerVideoUrl();
            this.durationSeconds = movie.getDurationSeconds();
            this.releaseDate = dateUtils.formatDate(movie.getReleaseDate());
            this.studioName = movie.getStudioName();
            this.cast = movie.getCast();
            this.ticket = TicketOutput.builder()
                .id(movie.getMovieTicket().getId())
                .normalPrice(movie.getMovieTicket().getNormalPrice().toString())
                .studentPrice(movie.getMovieTicket().getStudentPrice().toString())
                .specialPrice(movie.getMovieTicket().getSpecialPrice().toString())
                .isFree(movie.getMovieTicket().getIsFree())
                .build();
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class RoomOutput {
        private Long id;
        private String number;
        private Integer totalSeats;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class TicketOutput {
        private Long id;
        private String normalPrice;
        private String studentPrice;
        private String specialPrice;
        private Boolean isFree;
    }

    public static RoomOutput toRoomOutput(RoomSession roomSession) {
        return RoomOutput.builder()
            .id(roomSession.getRoom().getId())
            .number(roomSession.getRoom().getNumber())
            .totalSeats(roomSession.getRoom().getSeats().size())
            .build();
    }
}
