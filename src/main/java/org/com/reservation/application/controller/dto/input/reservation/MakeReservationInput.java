package org.com.reservation.application.controller.dto.input.reservation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MakeReservationInput {
    @NotEmpty
    private List<ReservationInput> reservations;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class ReservationInput {
        @NotNull
        private Long seatId;

        @NotBlank
        private String ticketType;
    }
}
