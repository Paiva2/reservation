package org.com.reservation.application.controller.dto.input.movie;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateMovieInput {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String posterImage;

    @NotBlank
    private String trailerVideoUrl;

    @Min(value = 0, message = "durationSeconds can't be zero.")
    private Long durationSeconds;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;

    @NotBlank
    private String studioName;

    private List<String> cast;
    private List<Long> genresIds;
    private MovieTicketInput ticket;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class MovieTicketInput {
        @NotBlank
        private String normalPrice;

        @NotBlank
        private String studentPrice;

        @NotBlank
        private String specialPrice;

        private Boolean isFree;
    }
}
