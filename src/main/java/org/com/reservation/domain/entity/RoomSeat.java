package org.com.reservation.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoomSeat {
    private KeyId id;
    private Room room;
    private Seat seat;
    private Date createdAt;
    private Date updatedAt;
    private List<ReservationRoomSeat> reservationRoomSeats;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class KeyId {
        private Long seatId;
        private Long roomId;
    }
}
