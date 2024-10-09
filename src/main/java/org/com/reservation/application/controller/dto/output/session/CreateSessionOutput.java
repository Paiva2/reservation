package org.com.reservation.application.controller.dto.output.session;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.com.reservation.domain.entity.Room;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateSessionOutput {
    private Long sessionId;
    private Date start;
    private Date end;
    private List<RoomOutput> room;
    private MovieOutput movie;

    public static RoomOutput convertRoomList(Room room) {
        return RoomOutput.builder()
            .id(room.getId())
            .number(room.getNumber())
            .build();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class RoomOutput {
        private Long id;
        private String number;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class MovieOutput {
        private Long id;
        private String title;
    }
}
