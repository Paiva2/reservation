package org.com.reservation.domain.entity;

import lombok.*;
import org.com.reservation.domain.enumeration.EnumTicketType;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReservationTicket {
    private Long id;
    private BigDecimal pricePaid;
    private EnumTicketType type;
    private Date createdAt;
    private Date updatedAt;

    private Reservation reservation;
    private MovieTicket movieTicket;
}
