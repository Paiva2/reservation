package org.com.reservation.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MovieGenre {
    private KeyId id;
    private Movie movie;
    private Genre genre;
    private Date createdAt;
    private Date updatedAt;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class KeyId {
        private Long movieId;
        private Long genreId;
    }
}
