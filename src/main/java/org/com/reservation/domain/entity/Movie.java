package org.com.reservation.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Movie {
    private Long id;
    private String title;
    private String description;
    private String posterImage;
    private String trailerVideoUrl;
    private Long durationSeconds;
    private Date releaseDate;
    private String studioName;
    private String cast;
    private List<MovieGenre> movieGenres;
    private List<Reservation> reservations;
    private Date createdAt;
    private Date updatedAt;
}
