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
public class Reservation {
    private Long id;
    private User user;
    private Movie movie;
    private List<ReservationRoomSeat> reservationRoomSeats;
    private Date createdAt;
    private Date updatedAt;
}
