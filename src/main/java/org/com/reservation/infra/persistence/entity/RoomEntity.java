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
@Table(name = "TB_ROOMS")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RO_ID")
    private Long id;

    @Column(name = "RO_NUMBER", nullable = false, unique = true)
    private String number;

    @CreationTimestamp
    @Column(name = "RO_CREATED_AT", updatable = false, nullable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "RO_UPDATED_AT", nullable = false)
    private Date updatedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    private List<RoomSeatEntity> seats;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    private List<RoomSessionEntity> roomSessions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    private List<RoomSeatEntity> roomSeats;
}
