package org.com.reservation.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.com.reservation.domain.enumeration.EnumTicketType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "TB_RESERVATIONS_TICKETS")
public class ReservationTicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "RT_PRICE_PAID", nullable = false)
    private BigDecimal pricePaid;

    @Enumerated(EnumType.STRING)
    @Column(name = "RT_TYPE", nullable = false)
    private EnumTicketType type;

    @CreationTimestamp
    @Column(name = "RT_CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "RT_UPDATED_AT", nullable = false)
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RT_RESERVATION_ID")
    private ReservationEntity reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RT_MOVIE_TICKET_ID")
    private MovieTicketEntity movieTicket;
}
