package org.com.reservation.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MovieTicket {
    private Long id;
    private BigDecimal normalPrice;
    private BigDecimal studentPrice;
    private BigDecimal specialPrice;
    private Boolean isFree;
    private Date createdAt;
    private Date updatedAt;

    private Movie movie;
    private List<ReservationMovieTicket> reservationMovieTickets;
}
