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
public class Session {
    private Long id;
    private Date start;
    private Date end;
    private Date createdAt;
    private Date updatedAt;

    private Movie movie;
    private List<RoomSession> roomSessions;
    private List<Reservation> reservations;
}
