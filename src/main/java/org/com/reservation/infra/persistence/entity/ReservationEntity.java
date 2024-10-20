package org.com.reservation.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "TB_RESERVATIONS")
@SequenceGenerator(name = "reservation_local_seq", sequenceName = "tb_reservations_res_id_seq", allocationSize = 1)
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_local_seq")
    @Column(name = "RES_ID")
    private Long id;

    @CreationTimestamp
    @Column(name = "RES_CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "RES_UPDATED_AT", nullable = false)
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RES_USER_ID")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RES_SESSION_ID")
    private SessionEntity session;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reservation")
    private List<ReservationTicketEntity> reservationTickets;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reservation")
    private List<ReservationRoomSeatEntity> reservationRoomSeats;
}
