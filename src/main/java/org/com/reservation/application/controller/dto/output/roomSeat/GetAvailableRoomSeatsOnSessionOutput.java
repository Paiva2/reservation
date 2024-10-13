package org.com.reservation.application.controller.dto.output.roomSeat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class GetAvailableRoomSeatsOnSessionOutput {
    private Long sessionId;
    private Long roomId;
    private Integer totalSeats;
    private List<SessionRoomSeatOutput> seats;

    @AllArgsConstructor
    @Builder
    @Data
    public static class SessionRoomSeatOutput {
        private Long seatId;
        private String row;
        private Integer number;
        private Boolean seatAvailable;
        private Boolean accessibility;
    }
}
