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
public class Genre {
    private Long id;
    private String name;
    private Date createdAt;
    private Date updatedAt;
    private List<MovieGenre> movieGenres;
}
