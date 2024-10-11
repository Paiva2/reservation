package org.com.reservation.application.controller.dto.output.session;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.com.reservation.domain.entity.Movie;
import org.com.reservation.domain.entity.RoomSession;
import org.com.reservation.domain.entity.Session;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ListUpcomingSessionsOutput {
    private Integer page;
    private Integer size;
    private Long totalItems;
    private Integer totalPages;
    private Boolean isLastPage;
    private List<SessionOutput> items;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class SessionOutput {
        private Long id;
        // todo: formatar
        private String start;
        // todo: formatar
        private String end;
        private Boolean active;

        private MovieOutput movie;
        private List<RoomOutput> rooms;

        public SessionOutput(Session session) {
            this.id = session.getId();
            this.start = session.getStart().toString();
            this.end = session.getEnd().toString();
            this.active = session.isActive();
            this.movie = new MovieOutput(session.getMovie());
            this.rooms = session.getRoomSessions().stream().map(ListUpcomingSessionsOutput::toRoomOutput).toList();
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
        private Date releaseDate;
        private String studioName;
        private String cast;

        public MovieOutput(Movie movie) {
            this.id = movie.getId();
            this.title = movie.getTitle();
            this.description = movie.getDescription();
            this.posterImage = movie.getPosterImage();
            this.trailerVideoUrl = movie.getTrailerVideoUrl();
            this.durationSeconds = movie.getDurationSeconds();
            this.releaseDate = movie.getReleaseDate();
            this.studioName = movie.getStudioName();
            this.cast = movie.getCast();
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class RoomOutput {
        private Long id;
        private String number;
    }

    public static RoomOutput toRoomOutput(RoomSession roomSession) {
        return RoomOutput.builder()
            .id(roomSession.getRoom().getId())
            .number(roomSession.getRoom().getNumber())
            .build();
    }
}
