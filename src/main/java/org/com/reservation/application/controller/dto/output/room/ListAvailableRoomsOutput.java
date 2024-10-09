package org.com.reservation.application.controller.dto.output.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.com.reservation.domain.entity.Room;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
//todo: add total seats
public class ListAvailableRoomsOutput {
    private Integer page;
    private Integer size;
    private Integer totalPages;
    private Long totalItems;
    private Boolean isLast;
    private List<RoomOutput> rooms;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class RoomOutput {
        private Long id;
        private String number;
    }

    public static RoomOutput convertRoom(Room room) {
        return RoomOutput.builder()
            .id(room.getId())
            .number(room.getNumber())
            .build();
    }
}
