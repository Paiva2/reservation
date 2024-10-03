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
public class Room {
    private Long id;
    private Integer number;
    private List<RoomSeat> seats;
    private List<MovieRoom> movieRooms;
    private Date createdAt;
    private Date updatedAt;
}
