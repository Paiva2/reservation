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
public class ReservationRoomSeat {
    private KeyId id;
    private Reservation reservation;
    private RoomSeat roomSeat;
    private Date createdAt;
    private Date updatedAt;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class KeyId {
        private Long reservationId;
        private RoomSeat.KeyId roomSeatIds;
    }
}
