package org.com.reservation.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "TB_RESERVATIONS_ROOM_SEAT")
public class ReservationRoomSeatEntity {
    @EmbeddedId
    private KeyId id;

    @CreationTimestamp
    @Column(name = "RRS_CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "RRS_UPDATED_AT", nullable = false)
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("reservationId")
    @JoinColumn(name = "RRS_RESERVATION_ID")
    private ReservationEntity reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roomSeatIds")
    @JoinColumns({
        @JoinColumn(name = "RRS_ROOM_ID", referencedColumnName = "RST_ROOM_ID"),
        @JoinColumn(name = "RRS_SEAT_ID", referencedColumnName = "RST_SEAT_ID")
    })
    private RoomSeatEntity roomSeat;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Embeddable
    public static class KeyId implements Serializable {
        @Column(name = "RRS_RESERVATION_ID")
        private Long reservationId;

        @Embedded
        @Columns(columns = {
            @Column(name = "RRS_ROOM_ID"),
            @Column(name = "RRS_SEAT_ID")
        })
        private RoomSeatEntity.KeyId roomSeatIds;
    }
}
