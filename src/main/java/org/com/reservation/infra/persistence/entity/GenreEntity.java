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
@Table(name = "TB_GENRES")
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GR_ID")
    private Long id;

    @Column(name = "GR_NAME", nullable = false, unique = true)
    private String name;

    @CreationTimestamp
    @Column(name = "GR_CREATED_AT", updatable = false, nullable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "GR_UPDATED_AT", nullable = false)
    private Date updatedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "genre")
    private List<MovieGenreEntity> movieGenres;
}
