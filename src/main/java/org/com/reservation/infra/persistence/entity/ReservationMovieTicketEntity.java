package org.com.reservation.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.com.reservation.domain.enumeration.EnumTicketType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "TB_RESERVATIONS_MOVIES_TICKETS")
public class ReservationMovieTicketEntity {
    @EmbeddedId
    private KeyId id;

    @Column(name = "RMT_PRICE_PAID", nullable = false)
    private BigDecimal pricePaid;

    @Enumerated(EnumType.STRING)
    @Column(name = "RMT_TYPE", nullable = false)
    private EnumTicketType type;

    @CreationTimestamp
    @Column(name = "RMT_CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "RMT_UPDATED_AT", nullable = false)
    private Date updatedAt;

    @MapsId("reservationId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RMT_RESERVATION_ID")
    private ReservationEntity reservation;

    @MapsId("movieTicketId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RMT_MOVIE_TICKET_ID")
    private MovieTicketEntity movieTicket;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Embeddable
    public static class KeyId implements Serializable {
        @Column(name = "RMT_RESERVATION_ID")
        private Long reservationId;

        @Column(name = "RMT_MOVIE_TICKET_ID")
        private Long movieTicketId;
    }
}
