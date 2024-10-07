package org.com.reservation.application.controller.input.user;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterMovieInput {
    @NotBlank
    private String title;

    private String description;

    private String posterImage;

    private String trailerVideoUrl;

    @NotNull
    private BigDecimal normalPrice;

    @NotNull
    private BigDecimal studentPrice;

    @NotNull
    private BigDecimal specialPrice;

    @NotNull
    private Boolean isFree;

    @NotNull
    @Min(value = 0)
    //todo: fix to a string ex: 2h 20m
    private Long durationSeconds;

    //todo: fix to accept yyyy-MM-dd
    private Date releaseDate;

    private String studioName;

    //todo: change to list and treat with ;
    private String cast;

    @NotEmpty
    private List<Long> movieGenresIds;
}
