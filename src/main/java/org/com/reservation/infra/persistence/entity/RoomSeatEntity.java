package org.com.reservation.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "TB_ROOMS_SEATS")
public class RoomSeatEntity {
    @EmbeddedId
    private KeyId id;

    @CreationTimestamp
    @Column(name = "RST_CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "RST_UPDATED_AT", nullable = false)
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("roomId")
    @JoinColumn(name = "RST_ROOM_ID")
    private RoomEntity room;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("seatId")
    @JoinColumn(name = "RST_SEAT_ID")
    private SeatEntity seat;

    @OneToMany(mappedBy = "roomSeat", fetch = FetchType.LAZY)
    private List<ReservationRoomSeatEntity> reservationRoomSeats;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Embeddable
    public static class KeyId implements Serializable {
        @Column(name = "RS_ROOM_ID")
        private Long roomId;

        @Column(name = "RS_SEAT_ID")
        private Long seatId;
    }
}
