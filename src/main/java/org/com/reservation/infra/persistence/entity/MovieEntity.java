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
@Table(name = "TB_MOVIES")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MO_ID")
    private Long id;

    @Column(name = "MO_TITLE", unique = true, nullable = false)
    private String title;

    @Column(name = "MO_DESCRIPTION", nullable = true)
    private String description;

    @Column(name = "MO_POSTER_IMAGE", nullable = true)
    private String posterImage;

    @Column(name = "MO_TRAILER_VIDEO_URL", nullable = true)
    private String trailerVideoUrl;

    @Column(name = "MO_DURATION_SECONDS", nullable = false)
    private Long durationSeconds;

    @Column(name = "MO_RELEASE_DATE", nullable = true)
    private Date releaseDate;

    @Column(name = "MO_STUDIO_NAME", nullable = true)
    private String studioName;

    @Column(name = "MO_CAST", nullable = true)
    private String cast;

    @CreationTimestamp
    @Column(name = "MO_CREATED_AT", nullable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "MO_UPDATED_AT", nullable = false)
    private Date updatedAt;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "movie")
    private MovieTicketEntity movieTicket;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    private List<MovieGenreEntity> movieGenres;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    private List<SessionEntity> sessions;
}
