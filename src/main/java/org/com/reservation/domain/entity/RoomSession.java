package org.com.reservation.domain.entity;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoomSession {
    private KeyId id;
    private Date createdAt;
    private Date updatedAt;

    private Room room;
    private Session session;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class KeyId {
        private Long sessionId;
        private Long roomId;
    }
}
