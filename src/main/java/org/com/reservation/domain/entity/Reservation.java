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
    private Date createdAt;
    private Date updatedAt;

    private User user;
    private Session session;
    private List<ReservationTicket> reservationTickets;
    private List<ReservationRoomSeat> reservationRoomSeats;
}
