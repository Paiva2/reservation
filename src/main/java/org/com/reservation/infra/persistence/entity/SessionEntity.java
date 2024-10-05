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
@Table(name = "TB_SESSIONS")
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "SS_ID")
    private Long id;

    @Column(name = "SS_START", nullable = false)
    private Date start;

    @Column(name = "SS_END", nullable = false)
    private Date end;

    @Column(name = "SS_CREATED_AT", updatable = false, nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "SS_UPDATED_AT", nullable = false)
    @UpdateTimestamp
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SS_MOVIE_ID")
    private MovieEntity movie;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "session")
    private List<RoomSessionEntity> roomSessions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "session")
    private List<ReservationEntity> reservations;
}
