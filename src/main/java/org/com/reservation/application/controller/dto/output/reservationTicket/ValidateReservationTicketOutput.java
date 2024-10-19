package org.com.reservation.application.controller.dto.output.reservationTicket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ValidateReservationTicketOutput {
    private Long reservationTicketId;
    private Long reservationId;
    private Boolean exists;
    private Boolean validForUse;
}
