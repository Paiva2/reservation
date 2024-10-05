package org.com.reservation.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.com.reservation.domain.enumeration.EnumSeatRow;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table
@Entity(name = "TB_SEATS")
public class SeatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ST_ID")
    private Long id;

    @Column(name = "ST_ROW", nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumSeatRow row;

    @Column(name = "ST_NUMBER", nullable = false)
    private Integer number;

    @Column(name = "ST_ACESSIBILITY", nullable = false)
    private Boolean accessibility;

    @Column(name = "ST_CREATED_AT", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "ST_UPDATED_AT", nullable = false)
    @UpdateTimestamp
    private Date updatedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "seat")
    private List<RoomSeatEntity> roomSeats;
}
