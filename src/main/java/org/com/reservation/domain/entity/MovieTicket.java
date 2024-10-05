package org.com.reservation.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.com.reservation.infra.persistence.entity.MovieEntity;

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

    private MovieEntity movie;
    private List<ReservationMovieTicket> reservationMovieTickets;
}
