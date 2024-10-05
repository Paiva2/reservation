package org.com.reservation.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "TB_ROOMS_SESSIONS")
public class RoomSessionEntity {
    @EmbeddedId
    private KeyId id;

    @Column(name = "RS_CREATED_AT", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "RS_UPDATED_AT", nullable = false)
    @CreationTimestamp
    private Date updatedAt;

    @MapsId("sessionId")
    @JoinColumn(name = "RS_SESSION_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private SessionEntity session;

    @MapsId("roomId")
    @JoinColumn(name = "RS_ROOM_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private RoomEntity room;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Embeddable
    public static class KeyId implements Serializable {
        @Column(name = "RS_SESSION_ID")
        private Long sessionId;

        @Column(name = "RS_ROOM_ID")
        private Long roomId;
    }
}
