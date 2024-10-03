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
public class MovieRoom {
    private KeyId id;
    private Date start;
    private Date end;
    private Date createdAt;
    private Date updatedAt;
    private Room room;
    private Movie movie;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class KeyId {
        private Long movieId;
        private Long roomId;
    }
}
