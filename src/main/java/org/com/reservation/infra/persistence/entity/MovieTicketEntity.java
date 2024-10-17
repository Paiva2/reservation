package org.com.reservation.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "TB_MOVIE_TICKETS")
@SequenceGenerator(name = "movie_ticket_local_seq", sequenceName = "tb_movie_tickets_mvt_id_seq", allocationSize = 1)
public class MovieTicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_ticket_local_seq")
    @Column(name = "MVT_ID")
    private Long id;

    @Column(name = "MVT_NORMAL_PRICE", nullable = false)
    private BigDecimal normalPrice;

    @Column(name = "MVT_STUDENT_PRICE", nullable = false)
    private BigDecimal studentPrice;

    @Column(name = "MVT_SPECIAL_PRICE", nullable = false)
    private BigDecimal specialPrice;

    @Column(name = "MVT_IS_FREE", nullable = false)
    private Boolean isFree;

    @CreationTimestamp
    @Column(name = "MVT_CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "MVT_UPDATED_AT")
    private Date updatedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MVT_MOVIE_ID")
    private MovieEntity movie;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movieTicket")
    private List<ReservationTicketEntity> reservationTickets;
}
