package org.com.reservation.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "TB_MOVIES_GENRES")
public class MovieGenreEntity {
    @EmbeddedId
    private KeyId id;

    @CreationTimestamp
    @Column(name = "MG_CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "MG_UPDATED_AT", nullable = false)
    private Date updatedAt;

    @MapsId("movieId")
    @JoinColumn(name = "MG_MOVIE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private MovieEntity movie;

    @MapsId("genreId")
    @JoinColumn(name = "MG_GENRE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenreEntity genre;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Embeddable
    public static class KeyId implements Serializable {
        @Column(name = "MG_MOVIE_ID")
        private Long movieId;

        @Column(name = "MG_GENRE_ID")
        private Long genreId;
    }
}
